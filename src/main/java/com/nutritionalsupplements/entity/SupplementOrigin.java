package com.nutritionalsupplements.entity;

public enum SupplementOrigin {
    not_assigned("Не указано"),
    animal("Животное"),
    vegetable("Растительное"),
    natural("Натуральное"),
    artificial("Искусственное"),
    synthetic("Синтетическое"),
    microbiological("Микробиологическое");

    SupplementOrigin(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static SupplementOrigin fromDescription(String description){
        for(SupplementOrigin value : SupplementOrigin.values()){
            if(value.getDescription().equalsIgnoreCase(description)){
                return value;
            }
        }
        return null;
    }
}