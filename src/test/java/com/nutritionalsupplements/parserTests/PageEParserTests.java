package com.nutritionalsupplements.parserTests;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageEParserTests {
    private Parser parser = new Parser();
    private String existingURL = "http://dobavkam.net/additives/e101";
    private String nonExistingURL = "http://dobavkam.net/additives/e121";

    private Supplement supplement = null;

    @Test
    public void testGetDescriptionSuccess() {
        supplement = parser.parseEPage(existingURL);
        System.out.println("_________________SUPPLEMENT_DATA____________________");
        System.out.println(supplement);
        System.out.println("_________________________________________________");
        Assert.assertEquals(Supplement.class, supplement.getClass());
    }

    @Test
    public void testGetDescriptionFailure() {
        supplement = parser.parseEPage(nonExistingURL);
        System.out.println("_________________SUPPLEMENT_DATA____________________");
        System.out.println(supplement);
        System.out.println("_________________________________________________");
        Assert.assertEquals(null, supplement);
    }
}
