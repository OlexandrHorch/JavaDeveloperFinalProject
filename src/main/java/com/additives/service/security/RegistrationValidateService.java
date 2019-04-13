package com.additives.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationValidateService {
    private Pattern pattern;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);

    }

    public RegistrationStatus validate(String email, String password, String firstName, String lastName) {
        if (!isEmailValid(email)) {
            return RegistrationStatus.invalidEmail;
        }

        if (userService.getByEmail(email) != null) {
            return RegistrationStatus.userExists;
        }

        if (!isPasswordValid(password)) {
            return RegistrationStatus.invalidPassword;
        }

        if (!isNameValid(firstName)) {
            return RegistrationStatus.invalidFirstName;
        }

        if (!isNameValid(lastName)) {
            return RegistrationStatus.invalidLastName;
        }

        return RegistrationStatus.ok;

    }

    private boolean isEmailValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 5;
    }

    private boolean isNameValid(String name) {
        return name != null && name.trim().length() > 0;
    }

    public enum RegistrationStatus {
        ok("Вы успешно зарегистрировались"),
        userExists("Пользователь с таким email уже существует"),
        invalidEmail("Неправильный почтовый адрес"),
        invalidPassword("Длина пароля должна составлять 5 или больше символов"),
        invalidFirstName("Введите ваше имя"),
        invalidLastName("Введите вашу фамилию");

        RegistrationStatus(String description) {
            this.description = description;
        }

        private String description;

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        String email = " test2@gmail.com";

        RegistrationValidateService validateService = new RegistrationValidateService();
        validateService.init();
        boolean result = validateService.isEmailValid(email);
        System.out.println(result);
    }
}
