package com.nutritionalsupplements.entity;

public enum SupplementEnumOrigin {
    ORIGIN1("ORIGIN1"), //змінити
    ORIGIN2("ORIGIN2"), //змінити
    ORIGIN3("ORIGIN3"); //змінити

    SupplementEnumOrigin(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}