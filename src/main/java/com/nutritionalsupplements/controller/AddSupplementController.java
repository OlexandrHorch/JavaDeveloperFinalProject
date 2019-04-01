package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.repository.SupplementRepository;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/add_supplement")
public class AddSupplementController {

    @Autowired
    private Parser parser;

    @Autowired
    private SupplementService supplementService;

    @GetMapping
    public String showPage(){
        return "/parser/add_supplement";
    }

    @ResponseBody
    @PostMapping
    public String addSupplement(@RequestParam(name = "searchString") String searchString){
        Supplement supplement = parser.parseEPage(searchString);
        if (supplement.getClass() == Supplement.class) {
            supplementService.saveSupplement(supplement);
            return supplement.toString();
        }   else {
            return "This supplement doesn`t exist on site dobavkam.net";
        }
    }
}
