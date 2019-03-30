package com.nutritionalsupplements.entity;

public enum SupplementDanger {
    very_low("Очень низкая"),
    low("Низкая"),
    middle("Средняя"),
    high("Высокая");

    SupplementDanger(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}