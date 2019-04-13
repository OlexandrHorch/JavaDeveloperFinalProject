package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.entity.SupplementCategory;
import com.nutritionalsupplements.entity.SupplementDanger;
import com.nutritionalsupplements.entity.SupplementOrigin;
import com.nutritionalsupplements.entity.security.User;
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
    public ModelAndView index(@RequestParam(required = false) String searchRequest,
                              @RequestParam(required = false) SupplementCategory supplementCategory,
                              @RequestParam(required = false) SupplementDanger supplementDanger,
                              @RequestParam(required = false) SupplementOrigin supplementOrigin) {
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

        result.addObject("supplementCategories", SupplementCategory.values());
        if (supplementCategory != null) {
            specs.add(SupplementSpecifications.withCategory(supplementCategory));
            result.addObject("supplementCategory", supplementCategory);
        }

        result.addObject("supplementDangers", SupplementDanger.values());
        if (supplementDanger != null) {
            specs.add(SupplementSpecifications.withDanger(supplementDanger));
            result.addObject("supplementDanger", supplementDanger);
        }

        result.addObject("supplementOrigins", SupplementOrigin.values());
        if (supplementOrigin != null) {
            specs.add(SupplementSpecifications.withOrigin(supplementOrigin));
            result.addObject("supplementOrigin", supplementOrigin);
        }

        List<Supplement> supplements = null;
        if (specs.size() > 0) {
            supplements = supplementService.query(SupplementSpecifications.and(specs), Sort.by(Sort.Direction.ASC, "eCod"));
        } else {
            supplements = supplementService.getSupplements();
        }

        result.addObject("supplements", supplements);

        result.addObject("user", userService.getUser());

        //SEO params
        result.addObject("title", "Список пищевых добавок с описаниями");
        result.addObject("description", "Сервис позволяет искать пищевые добавки по коду, опасности, происхождению, и предназначению. Для каждой добавки есть подробное описание");

        return result;
    }

    @GetMapping("/get-supplement/{id}")
    public String getSupplementById(@PathVariable(name = "id") Long id, Model model) {

        Supplement supplement = supplementService.getSupplement(id);
        model.addAttribute("supplement", supplement);

        model.addAttribute("user", userService.getUser());

        return "supplement";
    }

    @GetMapping("/supplement/change/{id}")
    public ModelAndView getSupplementChangeForm(@PathVariable(name = "id") Long id) {
        Supplement supplement = supplementService.getSupplement(id);

        ModelAndView result = new ModelAndView("supplement_form");
        result.addObject("supplement", supplement);
        result.addObject("title", "Список пищевых добавок с описаниями");
        result.addObject("description", "Сервис");
        return result;
    }

    @PostMapping("/supplement/change")
    public ModelAndView changeSupplement(Supplement supplement) {

        supplementService.updateSupplement(supplement);

        List<Supplement> supplements = supplementService.getSupplements();

        ModelAndView result = new ModelAndView("redirect:/");
        result.addObject("supplement", supplements);
        result.addObject("title", "Список пищевых добавок с описаниями");
        result.addObject("description", "Сервис");
        return result;
    }
}