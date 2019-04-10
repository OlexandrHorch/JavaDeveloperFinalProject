package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import com.nutritionalsupplements.service.SupplementSpecifications;
import com.nutritionalsupplements.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class SupplementController {

    @Autowired
    private SupplementService supplementService;

    @Autowired
    private UserService userService;

    @GetMapping("/get-supplement/{id}")
    public String getSupplementById(@PathVariable(name = "id") Long id, Model model) {

        Supplement supplement = supplementService.getSupplement(id);
        model.addAttribute("supplement", supplement);

        model.addAttribute("user", userService.getUser());

        return "supplement";
    }

    @GetMapping("/supplements")
    public String getSuplements(Model model) {

        List<Supplement> supplements = supplementService.getSupplements();

        model.addAttribute("supplements", supplements);

        model.addAttribute("user", userService.getUser());

        return "supplements";
    }

    private Supplement findByCodeName (String codeName){
        return supplementService.query(SupplementSpecifications.withNameContaining(codeName)).get(0);
    }

    @PostMapping("/supplement/change")
    @ResponseBody
    public Supplement changeSupplement(@RequestParam(name = "id") Long id, @RequestBody Supplement supplement) {
        supplement.setId(id);
        supplementService.updateSupplement(supplement);
        return supplement;
    }
}