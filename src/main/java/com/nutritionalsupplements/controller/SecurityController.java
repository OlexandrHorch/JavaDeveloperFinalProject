package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.security.User;
import com.nutritionalsupplements.service.security.RegistrationValidateService;
import com.nutritionalsupplements.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidateService registrationValidateService;

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/register")
    public ModelAndView register(@RequestParam(required = false)  RegistrationValidateService.RegistrationStatus status) {
        ModelAndView result = new ModelAndView("security/register");
        result.addObject("user", userService.getUser());

        if (status != null) {
            result.addObject("status", status);
        }

        return result;
    }

    @PostMapping("/register")
    public String handleRegister(String email, String password, String firstname) {

        email = email.trim();
        firstname = firstname.trim();

        RegistrationValidateService.RegistrationStatus status = registrationValidateService.validate(email, password, firstname, firstname);

        if (status == RegistrationValidateService.RegistrationStatus.ok) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(firstname);
            newUser.setLastName(firstname);
            userService.saveUser(newUser);
        }

        return "redirect:/register?status=" + status;
    }
}