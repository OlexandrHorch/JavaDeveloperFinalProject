package com.additives.controller;

import com.additives.entity.Additive;
import com.additives.entity.AdditiveCategory;
import com.additives.entity.AdditiveDanger;
import com.additives.entity.AdditiveOrigin;
import com.additives.service.AdditiveService;
import com.additives.service.AdditiveSpecifications;
import com.additives.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdditiveController {

    @Autowired
    private AdditiveService additiveService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String searchRequest,
                              @RequestParam(required = false) AdditiveCategory additiveCategory,
                              @RequestParam(required = false) AdditiveDanger additiveDanger,
                              @RequestParam(required = false) AdditiveOrigin additiveOrigin) {
        ModelAndView result = new ModelAndView("additives");

        List<Specification<Additive>> specs = new ArrayList<>();

        if (searchRequest != null && !searchRequest.trim().isEmpty()) {
            specs.add(AdditiveSpecifications.or(
                        AdditiveSpecifications.withOtherNamesLike(searchRequest),
                        AdditiveSpecifications.withNameContaining(searchRequest),
                        AdditiveSpecifications.withUsingInfoLike(searchRequest),
                        AdditiveSpecifications.withECodeLike(searchRequest)
                    ));
            result.addObject("searchRequest", searchRequest);
        }

        result.addObject("additiveCategories", AdditiveCategory.values());
        if (additiveCategory != null) {
            specs.add(AdditiveSpecifications.withCategory(additiveCategory));
            result.addObject("additiveCategory", additiveCategory);
        }

        result.addObject("additiveDangers", AdditiveDanger.values());
        if (additiveDanger != null) {
            specs.add(AdditiveSpecifications.withDanger(additiveDanger));
            result.addObject("additiveDanger", additiveDanger);
        }

        result.addObject("additiveOrigins", AdditiveOrigin.values());
        if (additiveOrigin != null) {
            specs.add(AdditiveSpecifications.withOrigin(additiveOrigin));
            result.addObject("additiveOrigin", additiveOrigin);
        }

        List<Additive> additives = null;
        if (specs.size() > 0) {
            additives = additiveService.query(AdditiveSpecifications.and(specs), Sort.by(Sort.Direction.ASC, "eCod"));
        } else {
            additives = additiveService.getAdditives();
        }

        result.addObject("additives", additives);

        result.addObject("user", userService.getUser());

        //SEO params
        result.addObject("title", "Список пищевых добавок с описаниями");
        result.addObject("description", "Сервис позволяет искать пищевые добавки по коду, опасности, происхождению, и предназначению. Для каждой добавки есть подробное описание");

        return result;
    }

    @GetMapping("/get-additive/{id}")
    public String getAdditiveById(@PathVariable(name = "id") Long id, Model model) {
        Additive additive = additiveService.getAdditive(id);
        model.addAttribute("additive", additive);

        String title = additive.getECod() + " (" + additive.getName() + ") - описание и свойства добавки";
        String description = title;

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        model.addAttribute("user", userService.getUser());

        return "additive";
    }

    @GetMapping("/additive/change/{id}")
    public ModelAndView getAdditiveChangeForm(@PathVariable(name = "id") Long id) {
        Additive additive = additiveService.getAdditive(id);

        ModelAndView result = new ModelAndView("additive_form");
        result.addObject("user", userService.getUser());
        result.addObject("additive", additive);
        result.addObject("title", "Список пищевых добавок с описаниями");
        result.addObject("description", "Сервис");
        return result;
    }

    @PostMapping("/additive/change")
    public ModelAndView changeAdditive(Additive additive) {
        additiveService.updateAdditive(additive);

        List<Additive> additives = additiveService.getAdditives();

        ModelAndView result = new ModelAndView("redirect:/");
        result.addObject("additive", additives);
        result.addObject("title", "Список пищевых добавок с описаниями");
        result.addObject("description", "Сервис");
        return result;
    }
}