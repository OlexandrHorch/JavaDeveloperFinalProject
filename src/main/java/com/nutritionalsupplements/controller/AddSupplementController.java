package com.nutritionalsupplements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/add_supplement")
public class AddSupplementController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(){
        return new ModelAndView("/parser/add_E");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addSupplement(){
        return new ModelAndView("/parser/add_E");
    }

    //public void addSupplement
}
