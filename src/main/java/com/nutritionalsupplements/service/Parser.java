package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.entity.SupplementCategory;
import com.nutritionalsupplements.entity.SupplementDanger;
import com.nutritionalsupplements.entity.SupplementOrigin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Document page = null;

    public String parseURLStringE(String string) {
        String eStart = "http://dobavkam.net/additives/";

        Pattern patternE = Pattern.compile("[eE]\\d{3,}|[eE].\\d{3,}");
        Matcher matcher = patternE.matcher(string);

        String result = matcher.find() ? matcher.group() : null;
        if (Pattern.matches("[eE]\\D\\d{3,}", result)) {
            result = result.substring(0, 1) + result.substring(2);
        }
        return eStart + result;
    }

    public String parseURLStringProduct(String string) {
        String productStart = "http://dobavkam.net/products/";

        if (Pattern.matches("(^http.+|dobavkam.+|www.+)", string)) {
            return string;
        } else {
            return productStart + string;
        }
    }

    public Supplement parseEPage(String dirtyURL) {
        Supplement supplement = new Supplement();

        String pageURL = parseURLStringE(dirtyURL);
        page = null;
        try {
            page = Jsoup.connect(pageURL)
                    .ignoreContentType(true)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = getNamesFromPageE();
        SupplementCategory category = SupplementCategory.fromDescription(getParameterByHtmlClassName("field categories"));
        SupplementDanger danger = SupplementDanger.fromDescription(getParameterByHtmlClassName("danger"));
        SupplementOrigin origin = SupplementOrigin.fromDescription(getParameterByHtmlClassName("origin"));

        supplement.setName(name);
        supplement.setCategory(category);
        supplement.setDanger(danger);
        supplement.setOrigin(origin);
        supplement.setGeneralInfo(getTextFromTitleToTitle("Общая информация","Влияние на организм Польза"));
        supplement.setBenefit(getTextFromTitleToTitle("Влияние на организм Польза","Вред"));
        supplement.setHarm(getTextFromTitleToTitle("Вред","Использование"));
        supplement.setUsing(getTextFromTitleToTitle("Использование","Законодательство"));
        supplement.setLegislation(getTextFromTitleToTitle("Законодательство"));

//      System.out.println(supplement.getName());
//      System.out.println(supplement.getOrigin());
//      System.out.println(supplement.getCategory());
//      System.out.println(supplement.getDanger());
//      System.out.println(supplement.getGeneralInfo());

        return supplement;
    }

    private String getNamesFromPageE() {
        String result = getParameterByHtmlClassName("name");
        String namesRaw = page.getElementsByClass("spoiler-body").get(0).text();
        String[] elements = namesRaw.split(",|\\.");
        for (int i = 0; i < elements.length; i++) {
            result += elements[i].trim() + "#";
        }
        return result;
    }

    private String getParameterByHtmlClassName(String name) {
        String parameterRaw = page.getElementsByClass(name).get(0).text();
        String[] parameterRaw2 = parameterRaw.split(":");
        String result = parameterRaw2[parameterRaw2.length - 1].trim();
        return result;
    }

    private String getContentFromPageE() {
        String content = page.getElementsByClass("content").get(0).text();
        return content;
    }

    private String removeTitleAndTextBeforeIt(String text, String title){
        return text.substring(text.indexOf(title)+title.length()+1,text.length());
    }

    private String getTextFromTitleToTitle(String titleFrom, String titleTo){
        String pageContent = getContentFromPageE();
        String contentWithoutTitle = removeTitleAndTextBeforeIt(pageContent,titleFrom);
        int endOfGeneralInfo = contentWithoutTitle.indexOf(titleTo);
        return contentWithoutTitle.substring(0, endOfGeneralInfo - 1);
    }

    private String getTextFromTitleToTitle(String titleFrom){
        String pageContent = getContentFromPageE();
        return removeTitleAndTextBeforeIt(pageContent,titleFrom);
    }
}
