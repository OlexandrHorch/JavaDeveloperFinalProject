package com.nutritionalsupplements.entity;

public enum SupplementEnumOrigin {
    ANIMAL("животное"),
    VEGETABLE("растительные"),
    ARTIFICIAL("искусственные"),
    SYNTHETIC("синтетические");

    SupplementEnumOrigin(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}