package com.nutritionalsupplements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeveloperController {
    @GetMapping("/developers")
    public String getDevelopers() {
        return "developers";
    }
}
