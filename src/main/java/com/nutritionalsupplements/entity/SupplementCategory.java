package com.nutritionalsupplements.entity;

public enum SupplementCategory {
    antioxidants("Антиоксиданты"),
    flavors("Ароматизаторы"),
    anti_caking_agents("Вещества против слеживания"),
    water_retaining_agents("Влагоудерживающие агенты"),
    waxes("Воски"),
    thickeners ("Загустители"),
    complexing_agents ("Комплексообразователи"),
    preservatives ("Консерванты"),
    colorants ("Красители"),
    frothers ("Пенообразователи"),
    sweeteners ("Подсластители"),
    baking_powder ("Разрыхлители"),
    acidity_regulators ("Регуляторы кислотности"),
    stabilizers ("Стабилизаторы"),
    texture_makers ("Текстураторы"),
    amplifiers_taste_and_aroma ("Усилители вкуса и аромата"),
    coloring_pins ("Фиксаторы окраски"),
    emulsifiers ("Эмульгаторы");

    SupplementCategory(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static SupplementCategory fromDescription(String description){
        for(SupplementCategory value : SupplementCategory.values()){
            if(value.getDescription().equalsIgnoreCase(description)){
                return value;
            }
        }
        return null;
    }
}

