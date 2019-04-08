package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SupplementService supplementService;

    @GetMapping("/get-supp-form")
    public String getAddSupplementForm(Model model) {
        Supplement supplement = new Supplement();
        model.addAttribute("supplement", supplement);

        return "add-supplement";
    }

    @PostMapping("/add-supp")
    public String addSupplement(Supplement supplement) {
        supplementService.saveSupplement(supplement);

        return "redirect:/";
    }

}