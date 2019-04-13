package com.additives.entity;

public enum AdditiveCategory {
    not_assigned("Не указана"),
    antioxidant("Антиоксиданты"),
    flavor("Ароматизаторы"),
    anti_caking_agent("Вещества против слеживания"),
    moisture_retaining_agent("Влагоудерживающие агенты"),
    wax("Воски"),
    thickener("Загустители"),
    complexing_agent("Комплексообразователи"),
    preservative("Консерванты"),
    colorant("Красители"),
    frother("Пенообразователи"),
    sweetener("Подсластители"),
    baking_powder("Разрыхлители"),
    acidity_regulator("Регуляторы кислотности"),
    stabilizer("Стабилизаторы"),
    texturator("Текстураторы"),
    amplifiers("Усилители вкуса и аромата"),
    color_retainer("Фиксаторы окраски"),
    emulsifier("Эмульгаторы");
  
    AdditiveCategory(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static AdditiveCategory fromDescription(String description){
        for(AdditiveCategory value : AdditiveCategory.values()){
            if(value.getDescription().equalsIgnoreCase(description)){
                return value;
            }
        }
        return null;
    }
}