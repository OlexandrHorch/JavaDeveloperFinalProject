package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/supplement_parser")
public class SupplementParserController {

    @Autowired
    private Parser parser;

    @Autowired
    private SupplementService supplementService;

    @GetMapping
    public String showPage(){
        return "parser/supplement_parser";
    }

    @PostMapping
    public ModelAndView addSupplement(@RequestParam(name = "searchString") String searchString){
        ModelAndView result = new ModelAndView("parser/supplement_parser");

        List<Supplement> supplements = parser.parseEPage(searchString);
        result.addObject("parsedSupplements", supplements);

        return result;
    }
}
