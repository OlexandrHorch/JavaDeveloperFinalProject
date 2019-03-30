package com.nutritionalsupplements.entity;

public enum SupplementOrigin {
    animal("животное"),
    vegetable("растительные"),
    artificial("искусственные"),
    synthetic("синтетические");

    SupplementOrigin(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}