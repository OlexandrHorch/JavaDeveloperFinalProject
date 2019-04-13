package com.additives.controller;

import com.additives.entity.Additive;
import com.additives.service.AdditiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdditiveService additiveService;

    @GetMapping()
    public String getAdminHomePage( Model model) {
        List<Additive> additives = additiveService.getAdditives();
        model.addAttribute("additives", additives);
        return "admin-home-additives";
    }

    @GetMapping("/get-supp-form")
    public String getAddAdditiveForm(Model model) {
        Additive additive = new Additive();
        model.addAttribute("additive", additive);

        return "add-additive";
    }

    @PostMapping("/add-supp")
    public String addAdditive(Additive additive) {
        additiveService.saveAdditive(additive);

        return "redirect:/";
    }

    @GetMapping ("/deleteAdditive/{id}")
    public String deleteAdditiveById(@PathVariable(name = "id") Long id) {

        Additive additive = additiveService.getAdditive(id);
        additiveService.removeAdditive(additive);

        return "redirect:/";
    }

}