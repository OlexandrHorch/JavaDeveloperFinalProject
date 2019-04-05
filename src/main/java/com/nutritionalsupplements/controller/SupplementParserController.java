package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.repository.SupplementRepository;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parser")
public class SupplementParserController {

    @Autowired
    private Parser parser;

    @Autowired
    private SupplementService supplementService;

    @GetMapping
    public String showPage(){
        return "parser/add_supplement";
    }

    @ResponseBody
    @PostMapping
    public String addSupplement(@RequestParam(name = "searchString") String searchString){
        try {
            Supplement supplement = parser.parseEPage(searchString);
            supplementService.saveSupplement(supplement);
            return "Добавка успешно добавлена, название: " + supplementService.getFirstNameOrDefault(supplement, " <непонятно>");
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Не получилось спарсить добавку";
        }
    }
}
