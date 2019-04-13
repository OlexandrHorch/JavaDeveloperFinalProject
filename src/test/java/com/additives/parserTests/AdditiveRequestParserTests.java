package com.additives.parserTests;

import com.additives.service.Parser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdditiveRequestParserTests {
    private String requestToParse = "e101, e102103";

    @Autowired
    private Parser parser;

    @Test
    public void testParser() {
        HashSet<String> urls = parser.parseRequestStringE(requestToParse);
        for (String url : urls) {
            System.out.println(url);
        }
        Assert.assertTrue(urls.contains("http://dobavkam.net/additives/e101") && urls.contains("http://dobavkam.net/additives/e102") && urls.contains("http://dobavkam.net/additives/e103"));
    }
}
