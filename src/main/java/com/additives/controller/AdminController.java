package com.additives.controller;

import com.additives.entity.Additive;
import com.additives.service.AdditiveService;
import com.additives.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdditiveService additiveService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAdminHomePage( Model model) {
        List<Additive> additives = additiveService.getAdditives();
        model.addAttribute("additives", additives);
        return "admin-home-additives";
    }

    @GetMapping("/get-additive-form")
    public String getAddAdditiveForm(Model model) {
        Additive additive = new Additive();
        model.addAttribute("user", userService.getUser());
        model.addAttribute("additive", additive);

        return "add-additive";
    }

    @PostMapping("/add-additive")
    public String addAdditive(Additive additive, Model model) {
        additiveService.saveAdditive(additive);

        List<Additive> additives = additiveService.getAdditives();

        ModelAndView result = new ModelAndView("redirect:/");
        model.addAttribute("additive", additives);

        return "redirect:/";
    }

    @GetMapping ("/deleteAdditive/{id}")
    public String deleteAdditiveById(@PathVariable(name = "id") Long id) {

        Additive additive = additiveService.getAdditive(id);
        additiveService.removeAdditive(additive);

        return "redirect:/";
    }

}