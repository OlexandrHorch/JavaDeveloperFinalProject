package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.entity.SupplementCategory;
import com.nutritionalsupplements.entity.SupplementDanger;
import com.nutritionalsupplements.entity.SupplementOrigin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Parser {
    private Document page = null;

    @Autowired
    private SupplementService supplementService;

    public HashSet<String> parseRequestStringE(String string) {
        String eStart = "http://dobavkam.net/additives/e";
        HashSet<String> urls = new HashSet<>();

        Pattern patternE = Pattern.compile("\\d{3}");
        Matcher matcher = patternE.matcher(string);

        while (matcher.find()){
            urls.add(eStart + matcher.group());
        }

        return urls;
    }

    public String parseURLStringProduct(String string) {
        String productStart = "http://dobavkam.net/products/";

        if (Pattern.matches("(^http.+|dobavkam.+|www.+)", string)) {
            return string;
        } else {
            return productStart + string;
        }
    }

    public List<Supplement> parseEPage(String request) {
        List<Supplement> supplements = new ArrayList<>();

        for (String url : parseRequestStringE(request)) {
            Supplement supplement = new Supplement();
            page = null;

            try {
                page = Jsoup.connect(url)
                        .ignoreContentType(true)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (page == null) {     //Если страницы под нашу добавку не существует,
                continue;           //пропустить её анализ и перейти к следующей добавке.
            }

            supplement.setECod(getParameterByHtmlClassName("name"));
            supplement.setName(getNameFromPageE());
            supplement.setOther_names(getNamesFromPageE());
            supplement.setCategory(SupplementCategory.fromDescription(getParameterByHtmlClassName("field categories")));
            supplement.setDanger(SupplementDanger.fromDescription(getParameterByHtmlClassName("danger")));
            supplement.setOrigin(SupplementOrigin.fromDescription(getParameterByHtmlClassName("origin")));
            supplement.setGeneralInfo(getTextByHtmlClassName("Общая информация"));
            supplement.setBenefit(getTextByHtmlClassName("Польза"));
            supplement.setHarm(getTextByHtmlClassName("Вред"));
            supplement.setUsing_info(getTextByHtmlClassName("Использование"));
            supplement.setLegislation(getTextByHtmlClassName("Законодательство"));

            supplements.add(supplement);
        }

        supplementService.saveAll(supplements);

        return supplements;
    }

    private String getNameFromPageE(){
        String nameRaw = page.getElementById("page-title").text();
        //String[] nameRawParts = nameRaw.split("-");
        return nameRaw.substring(7);
    }

    private String getNamesFromPageE() {
        StringJoiner result = new StringJoiner("#");
        Elements jsoupElements = page.getElementsByClass("spoiler-body");
        if (jsoupElements.isEmpty())  {
            return null;
        }
        String namesRaw = jsoupElements.get(0).text();
        String[] elements = namesRaw.split(",|\\.");
        for (int i = 0; i < elements.length; i++) {
            String alternativeName = elements[i].trim();
            if (!alternativeName.isEmpty()) {
                result.add(elements[i].trim());
            }
        }
        return result.toString();
    }

    private String getParameterByHtmlClassName(String name) {
        Elements elements = page.getElementsByClass(name);
        if (elements.isEmpty())  {
            return null;
        }
        String containingParameter = elements.get(0).text();
        String[] temp = containingParameter.split(":");
        return temp[temp.length - 1].trim();
    }

    private String getTextByHtmlClassName(String name) {
        String result = "";
        Elements elements = page.select("h2:contains(" + name + ")");
        if (elements.isEmpty())  {
            elements = page.select("h3:contains(" + name + ")");
            if (elements.isEmpty()){
                return null;
            }
        }
        Element titleElement = elements.get(0);
        for (Element sibling = titleElement.nextElementSibling(); sibling != null; sibling = sibling.nextElementSibling()) {
            if ("h2".equals(sibling.tagName())||"h3".equals(sibling.tagName())) {
                return result;
            }
            result += sibling.text();
        }
        return result;
    }

    public void doSmth(){
        Supplement supplement = new Supplement();
        supplement.setGeneralInfo("description");
    }

}
