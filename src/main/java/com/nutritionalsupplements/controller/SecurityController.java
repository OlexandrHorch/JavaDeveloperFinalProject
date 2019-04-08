package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

}