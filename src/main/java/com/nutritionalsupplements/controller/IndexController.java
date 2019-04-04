package com.nutritionalsupplements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("home");
        return result;
    }


    @GetMapping("/supplement")
    public String showSupplement() {
        return "supplement";
    }

    @GetMapping("/supplements")
    public String showSupplements() {
        return "supplements";
    }

    @GetMapping("/products")
    public String showProducts() {
        return "products";
    }

    @GetMapping("/developers")
    public String showDevelopers() {
        return "developers";
    }

}