package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import com.nutritionalsupplements.service.SupplementSpecifications;
import com.nutritionalsupplements.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class SupplementController {

    @Autowired
    private SupplementService supplementService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String searchRequest) {
        ModelAndView result = new ModelAndView("supplements");

        List<Specification<Supplement>> specs = new ArrayList<>();

        if (searchRequest != null && !searchRequest.trim().isEmpty()) {
            specs.add(SupplementSpecifications.or(
                        SupplementSpecifications.withOtherNamesLike(searchRequest),
                        SupplementSpecifications.withNameContaining(searchRequest),
                        SupplementSpecifications.withUsingInfoLike(searchRequest),
                        SupplementSpecifications.withECodeLike(searchRequest)
                    ));
            result.addObject("searchRequest", searchRequest);
        }

        List<Supplement> supplements = null;
        if (specs.size() > 0) {
            supplements = supplementService.query(SupplementSpecifications.and(specs), Sort.by(Sort.Direction.ASC, "eCod"));
        } else {
            supplements = supplementService.getSupplements();
        }

        result.addObject("supplements", supplements);

        result.addObject("user", userService.getUser());

        return result;
    }

    @GetMapping("/get-supplement/{id}")
    public String getSupplementById(@PathVariable(name = "id") Long id, Model model) {

        Supplement supplement = supplementService.getSupplement(id);
        model.addAttribute("supplement", supplement);

        model.addAttribute("user", userService.getUser());

        return "supplement";
    }

    @PostMapping("/supplement/change")
    @ResponseBody
    public Supplement changeSupplement(@RequestParam(name = "id") Long id, @RequestBody Supplement supplement) {
        supplement.setId(id);
        supplementService.updateSupplement(supplement);
        return supplement;
    }
}