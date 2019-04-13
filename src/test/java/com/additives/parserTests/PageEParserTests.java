package com.additives.parserTests;

import com.additives.entity.Additive;
import com.additives.service.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageEParserTests {
    private Parser parser = new Parser();
    private String existingURL = "e459";
    private String nonExistingURL = "http://dobavkam.net/additives/e121";

    private List<Additive> additives = new ArrayList<>();

    @Before
    public void initial(){
        additives = parser.parseEPage(existingURL);
    }

    @Test
    public void testGetSupplementSuccess() {
        System.out.println("_________________SUPPLEMENT_DATA____________________");
        for (Additive additive : additives)
        System.out.println(additive);
        System.out.println("_________________________________________________");
        Assert.assertNotEquals(0, additives.size());
    }

    @Test
    public void testGetIdSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getId());
    }

    @Test
    public void testGetECodeSuccess() {
        for (Additive additive : additives)
            Assert.assertNotEquals(null, additive.getECod());
    }

    @Test
    public void testGetNameSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getName());
    }

    @Test
    public void testGetOther_namesSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getOtherNames());
    }

    @Test
    public void testGetCategorySuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getCategory());
    }

    @Test
    public void testGetOriginSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getOrigin());
    }

    @Test
    public void testGetDangerSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getDanger());
    }

    @Test
    public void testGetUsing_infoSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getUsingInfo());
    }

    @Test
    public void testGetGeneralInfoSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getGeneralInfo());
    }

    @Test
    public void testGetBenefitSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getBenefit());
    }

    @Test
    public void testGetHarmSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getHarm());
    }

    @Test
    public void testGetLegislationSuccess() {
        for (Additive additive : additives)
        Assert.assertNotEquals(null, additive.getLegislation());
    }

    @Test
    public void testGetDescriptionFailure() {
        additives = parser.parseEPage(existingURL);
        Assert.assertEquals(0, additives.size());
    }
}
