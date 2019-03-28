package com.nutritionalsupplements.entity;

public enum SupplementEnumDanger {
    VERY_LOW("Очень низкая"),
    LOW("Низкая"),
    MIDDLE("Средняя"),
    HIGH("Высокая");

    SupplementEnumDanger(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}