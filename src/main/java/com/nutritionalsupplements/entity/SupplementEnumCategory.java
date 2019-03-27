package com.nutritionalsupplements.entity;

public enum SupplementEnumCategory {
    CATEGORY1("CATEGORY1"), //змінити
    CATEGORY2("CATEGORY2"), //змінити
    CATEGORY3("CATEGORY3"); //змінити

    SupplementEnumCategory(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}