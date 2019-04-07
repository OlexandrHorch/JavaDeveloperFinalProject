package com.nutritionalsupplements.parserTests;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
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
    private String existingURL = "e111";
    private String nonExistingURL = "http://dobavkam.net/additives/e121";

    private List<Supplement> supplements = new ArrayList<>();

    @Before
    public void initial(){
        supplements = parser.parseEPage(existingURL);
    }

    @Test
    public void testGetSupplementSuccess() {
        System.out.println("_________________SUPPLEMENT_DATA____________________");
        for (Supplement supplement : supplements)
        System.out.println(supplement);
        System.out.println("_________________________________________________");
        Assert.assertNotEquals(0, supplements.size());
    }

    @Test
    public void testGetIdSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getId());
    }

    @Test
    public void testGetECodeSuccess() {
        for (Supplement supplement : supplements)
            Assert.assertNotEquals(null, supplement.getECod());
    }

    @Test
    public void testGetNameSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getName());
    }

    @Test
    public void testGetOther_namesSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getOther_names());
    }

    @Test
    public void testGetCategorySuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getCategory());
    }

    @Test
    public void testGetOriginSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getOrigin());
    }

    @Test
    public void testGetDangerSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getDanger());
    }

    @Test
    public void testGetUsing_infoSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getUsing_info());
    }

    @Test
    public void testGetGeneralInfoSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getGeneralInfo());
    }

    @Test
    public void testGetBenefitSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getBenefit());
    }

    @Test
    public void testGetHarmSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getHarm());
    }

    @Test
    public void testGetLegislationSuccess() {
        for (Supplement supplement : supplements)
        Assert.assertNotEquals(null, supplement.getLegislation());
    }

    @Test
    public void testGetDescriptionFailure() {
        supplements = parser.parseEPage(nonExistingURL);
        Assert.assertEquals(0, supplements.size());
    }
}
