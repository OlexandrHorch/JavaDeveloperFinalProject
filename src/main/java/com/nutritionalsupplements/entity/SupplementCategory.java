package com.nutritionalsupplements.entity;

public enum SupplementCategory {

    antioxidants("Антиоксиданты"),
    flavors("Ароматизаторы"),
    anti_caking_agents("Вещества против слеживания");
    // добавить остальные http://dobavkam.net/additives

    SupplementCategory(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

}
