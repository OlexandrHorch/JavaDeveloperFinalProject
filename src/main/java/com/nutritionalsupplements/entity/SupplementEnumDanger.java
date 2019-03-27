package com.nutritionalsupplements.entity;

public enum SupplementEnumDanger {
    DANGER1("DANGER1"), //змінити
    DANGER2("DANGER2"), //змінити
    DANGER3("DANGER3"); //змінити

    SupplementEnumDanger(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}