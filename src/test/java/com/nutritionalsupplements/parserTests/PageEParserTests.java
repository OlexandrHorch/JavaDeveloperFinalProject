package com.nutritionalsupplements.parserTests;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageEParserTests {
    private Parser parser = new Parser();
    private String pageURL = "http://dobavkam.net/additives/e101";
    private Supplement supplement = null;

    @Before
    public void getSupplimentFromURL() {
        supplement = parser.parseEPage(pageURL);
    }

    @Test
    public void testGetDescription() {
        supplement = parser.parseEPage(pageURL);
        System.out.println("_________________SUPPLEMENT_DATA____________________");
        System.out.println(supplement);
        System.out.println("_________________________________________________");
        Assert.assertNotEquals(null, supplement);
    }
}
