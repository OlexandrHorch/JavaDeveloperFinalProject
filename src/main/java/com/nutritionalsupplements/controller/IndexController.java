package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.SupplementService;
import com.nutritionalsupplements.service.SupplementSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private SupplementService supplementService;


    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("home");
        return result;
    }

    @GetMapping("/supplement")
    public ModelAndView getSupplement(@RequestParam(required = false, defaultValue = "") String searchSupplement) {
        ModelAndView result = new ModelAndView("/supplement");

        List<Specification<Supplement>> specs = new ArrayList<>();

        if (!searchSupplement.trim().isEmpty()) {
            specs.add(SupplementSpecifications.withOtherNamesLike(searchSupplement));
            result.addObject("searchSupplement", searchSupplement);
        }

        result.addObject("supplements",
                supplementService.query(
                        SupplementSpecifications.and(specs),
                        Sort.by(Sort.Direction.ASC, "eCod")));

        return result;
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