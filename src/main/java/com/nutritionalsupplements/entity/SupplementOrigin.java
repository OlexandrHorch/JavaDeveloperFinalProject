package com.nutritionalsupplements.entity;

public enum SupplementOrigin {
    animal("животное"),
    vegetable("растительные"),
    artificial("искусственное"),
    synthetic("синтетические");

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