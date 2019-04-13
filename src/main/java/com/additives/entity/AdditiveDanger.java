package com.additives.entity;

public enum AdditiveDanger {
    not_assigned("Не указана"),
    zero("Нулевая"),
    very_low("Очень низкая"),
    low("Низкая"),
    middle("Средняя"),
    high("Высокая"),
    very_high("Очень высокая");

    AdditiveDanger(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static AdditiveDanger fromDescription(String description){
        for(AdditiveDanger value : AdditiveDanger.values()){
            if(value.getDescription().equalsIgnoreCase(description)){
                return value;
            }
        }
        return null;
    }
}