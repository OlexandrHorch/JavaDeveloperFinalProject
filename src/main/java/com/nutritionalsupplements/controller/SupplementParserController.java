package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @PostMapping
    public String addSupplement(@RequestParam(name = "searchString") String searchString){
        List<Supplement> supplements = parser.parseEPage(searchString);
        if (supplements != null) {
            String result = "<h1>Добавки были найдены и добавлены в базу даных<br></h1>";
            for (Supplement supplement : supplements){
                supplementService.saveSupplement(supplement);
                result += supplement + "<br><br>";
            }
            return result;
        }   else {
            return "<H1>Этой добавки нет на сайте dobavkam.net</H1>";
        }
    }
}
