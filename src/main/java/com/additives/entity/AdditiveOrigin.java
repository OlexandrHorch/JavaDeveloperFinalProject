package com.additives.entity;

public enum AdditiveOrigin {
    not_assigned("Не указано"),
    animal("Животное"),
    vegetable("Растительное"),
    natural("Натуральное"),
    artificial("Искусственное"),
    synthetic("Синтетическое"),
    microbiological("Микробиологическое");

    AdditiveOrigin(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static AdditiveOrigin fromDescription(String description){
        for(AdditiveOrigin value : AdditiveOrigin.values()){
            if(value.getDescription().equalsIgnoreCase(description)){
                return value;
            }
        }
        return null;
    }
}