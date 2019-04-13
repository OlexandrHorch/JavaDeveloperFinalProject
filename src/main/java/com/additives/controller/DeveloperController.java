package com.additives.controller;

import com.additives.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeveloperController {
    @Autowired
    private UserService userService;

    @GetMapping("/developers")
    public ModelAndView getDevelopers() {
        ModelAndView result = new ModelAndView("developers");

        result.addObject("user", userService.getUser());

        return result;
    }
}
