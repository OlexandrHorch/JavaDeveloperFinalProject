package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.entity.SupplementCategory;
import com.nutritionalsupplements.entity.SupplementDanger;
import com.nutritionalsupplements.entity.SupplementOrigin;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
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
        org.jsoup.nodes.Document page = null;
        try {
            page = Jsoup.connect(pageURL)
                    .ignoreContentType(true)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = getNamesFromPageE(page);
        SupplementCategory category = SupplementCategory.fromDescription(getParameterByClassName(page, "field categories"));
        SupplementDanger danger = SupplementDanger.fromDescription(getParameterByClassName(page, "danger"));
        SupplementOrigin origin = SupplementOrigin.fromDescription(getParameterByClassName(page, "origin"));
        String description = getDescriptionFromPageE(page);

        supplement.setName(name);
        supplement.setCategory(category);
        supplement.setDanger(danger);
        supplement.setOrigin(origin);
        supplement.setGeneralInfo(description);

//        System.out.println(supplement.getName());
//        System.out.println(supplement.getOrigin());
//        System.out.println(supplement.getCategory());
//        System.out.println(supplement.getDanger());
//        System.out.println(supplement.getGeneralInfo());

        return supplement;
    }

    private String getNamesFromPageE(org.jsoup.nodes.Document document) {
        String result = getParameterByClassName(document, "name");
        String namesRaw = document.getElementsByClass("spoiler-body").get(0).text();
        String[] elements = namesRaw.split(",|\\.");
        for (int i = 0; i < elements.length; i++) {
            result += elements[i].trim() + "#";
        }
        return result;
    }

    public String getParameterByClassName(org.jsoup.nodes.Document document, String name) {
        String parameterRaw = document.getElementsByClass(name).get(0).text();
        String[] parameterRaw2 = parameterRaw.split(":");
        String result = parameterRaw2[parameterRaw2.length - 1].trim();
        return result;
    }

    public String getDescriptionFromPageE(org.jsoup.nodes.Document document) {
        String categoryRaw = document.getElementsByClass("content").get(0).text();
        String[] categoryRaw2 = categoryRaw.split(":");
        String category = categoryRaw2[categoryRaw2.length - 1].trim();
        return categoryRaw;
    }
}
