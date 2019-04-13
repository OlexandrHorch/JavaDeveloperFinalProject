package com.additives.service;

import com.additives.entity.Additive;
import com.additives.entity.AdditiveCategory;
import com.additives.entity.AdditiveDanger;
import com.additives.entity.AdditiveOrigin;
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
    private AdditiveService additiveService;

    public HashSet<String> parseRequestStringE(String string) {
        String eStart = "http://dobavkam.net/additives/e";
        HashSet<String> urls = new HashSet<>();

        Pattern patternE = Pattern.compile("\\d{3}");
        Matcher matcher = patternE.matcher(string);

        while (matcher.find()) {
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

    public List<Additive> parseEPage(String request) {
        List<Additive> additives = new ArrayList<>();

        for (String url : parseRequestStringE(request)) {
            Additive additive = new Additive();
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

            additive.setECod(getParameterByHtmlClassName("name"));
            additive.setName(getNameFromPageE());
            additive.setOtherNames(getNamesFromPageE());
            additive.setCategory(getCategoryFromPageE());
            additive.setDanger(getDangerFromPageE());
            additive.setOrigin(getOriginFromPageE());
            additive.setGeneralInfo(getTextByHtmlClassName("Общая информация"));
            additive.setBenefit(getTextByHtmlClassName("Польза"));
            additive.setHarm(getTextByHtmlClassName("Вред"));
            additive.setUsingInfo(getTextByHtmlClassName("Использование"));
            additive.setLegislation(getTextByHtmlClassName("Законодательство"));

            if (additive.getDanger() == AdditiveDanger.not_assigned && additive.getOrigin() == AdditiveOrigin.not_assigned && additive.getCategory() == AdditiveCategory.not_assigned) {
                continue;
            }
            additives.add(additive);
        }

        additiveService.saveAll(additives);

        return additives;
    }

    private String getNameFromPageE() {
        String nameRaw = page.getElementById("page-title").text();
        return nameRaw.substring(7);
    }

    private String getNamesFromPageE() {
        StringJoiner result = new StringJoiner("#");
        Elements jsoupElements = page.getElementsByClass("spoiler-body");
        if (jsoupElements.isEmpty()) {
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

    private AdditiveCategory getCategoryFromPageE() {
        String result = getParameterByHtmlClassName("field categories");
        if (result == null) {
            return AdditiveCategory.not_assigned;
        }
        if (result.toLowerCase().contains("эмульгаторы")) {
            result = "эмульгаторы";
        }
        if (result.toLowerCase().contains("красители")) {
            result = "красители";
        }
        return AdditiveCategory.fromDescription(result);
    }

    private AdditiveOrigin getOriginFromPageE() {
        String result = getParameterByHtmlClassName("origin");
        if (result == null) {
            return AdditiveOrigin.not_assigned;
        }
        if (result.contains("натуральное")) {
            result = "натуральное";
        }
        if (result.contains("искусственное")) {
            result = "искусственное";
        }
        if (result.contains("синтетическое")) {
            result = "синтетическое";
        }
        return AdditiveOrigin.fromDescription(result);
    }

    private AdditiveDanger getDangerFromPageE() {
        String rawTexst = getParameterByHtmlClassName("danger");
        if (rawTexst == null) {
            return AdditiveDanger.not_assigned;
        }
        return AdditiveDanger.fromDescription(rawTexst);
    }


    private String getParameterByHtmlClassName(String name) {
        Elements elements = page.getElementsByClass(name);
        if (elements.isEmpty()) {
            return null;
        }
        String containingParameter = elements.get(0).text();
        String[] temp = containingParameter.split(":");
        return temp[temp.length - 1].trim();
    }

    private String getTextByHtmlClassName(String name) {
        String result = "";
        Elements elements = page.select("h2:contains(" + name + ")");
        if (elements.isEmpty()) {
            elements = page.select("h3:contains(" + name + ")");
            if (elements.isEmpty()) {
                return null;
            }
        }
        Element titleElement = elements.get(0);
        for (Element sibling = titleElement.nextElementSibling(); sibling != null; sibling = sibling.nextElementSibling()) {
            if ("h2".equals(sibling.tagName()) || "h3".equals(sibling.tagName())) {
                return result;
            }
            result += sibling.text();
        }
        return result;
    }

    public void doSmth() {
        Additive additive = new Additive();
        additive.setGeneralInfo("description");
    }

}