package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import com.nutritionalsupplements.service.SupplementSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SupplementController {
    @Autowired
    private SupplementService supplementService;

    private Supplement findByCodeName (String codeName){
        return supplementService.query(SupplementSpecifications.withNameContaining(codeName)).get(0);
    }


    @RequestMapping(value = "/supplement", method = RequestMethod.GET)
    @ResponseBody
    public List<Supplement> findByName (@RequestParam("name") String codeName){
        return supplementService.query(SupplementSpecifications.withNameContaining(codeName));
    }


    @PostMapping
    @ResponseBody
    public Supplement changeSupplement(@RequestParam(name = "id") Long id, @RequestBody Supplement supplement) {
        supplement.setId(id);
        supplementService.updateSupplement(supplement);
        return supplement;
    }
}