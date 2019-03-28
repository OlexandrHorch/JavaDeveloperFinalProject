package com.nutritionalsupplements.entity;

public enum SupplementEnumCategory {
    ANTIOXIDANTS("Антиоксиданты"),
    FLAVORS("Ароматизаторы"),
    ANTI_CAKING_AGENTS("Вещества против слеживания");
    // добавить остальные http://dobavkam.net/additives

    SupplementEnumCategory(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}