package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import com.nutritionalsupplements.service.SupplementSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SupplementController {
    @Autowired
    private SupplementService supplementService;

    private Supplement findByCodeName (String codeName){
        return supplementService.query(SupplementSpecifications.withNameContaining(codeName)).get(0);
    }
}