//package com.nutritionalsupplements.controller;
//
//import com.nutritionalsupplements.entity.Supplement;
//import com.nutritionalsupplements.entity.SupplementEnumDanger;
//import com.nutritionalsupplements.service.SupplementService;
//import com.nutritionalsupplements.service.QuoteSpecifications;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/moderate")
//public class ModeratorController {
//    @Autowired
//    private SupplementService quoteService;
//
//    @GetMapping
//    public ModelAndView showModeratePanel(@RequestParam(name = "waiting", required = false, defaultValue = "false") boolean waiting,
//                                          @RequestParam(name = "approved", required = false, defaultValue = "false") boolean approved,
//                                          @RequestParam(name = "declined", required = false, defaultValue = "false") boolean declined) {
//
//        if (!( waiting || approved || declined )) {
//            waiting = true;
//        }
//
//        ModelAndView result = new ModelAndView("moderator/list");
//
//        List<Specification<Supplement>> specs = new ArrayList<>();
//
//        if (waiting) {
//            specs.add(QuoteSpecifications.withStatus(SupplementEnumDanger.waiting));
//        }
//
//        if (approved) {
//            specs.add(QuoteSpecifications.withStatus(SupplementEnumDanger.approved));
//        }
//
//        if (declined) {
//            specs.add(QuoteSpecifications.withStatus(SupplementEnumDanger.declined));
//        }
//
//        result.addObject("quotes", quoteService.query(QuoteSpecifications.or(specs)));
//
//        result.addObject("waiting", waiting);
//        result.addObject("approved", approved);
//        result.addObject("declined", declined);
//
//        return result;
//    }
//
//    @GetMapping("/setStatus")
//    public String setQuoteStatus(@RequestParam(name = "id") long id,
//                                 @RequestParam(name = "status") SupplementEnumDanger status) {
//        quoteService.setQuoteStatus(id, status);
//
//        return "redirect:/moderate";
//    }
//}