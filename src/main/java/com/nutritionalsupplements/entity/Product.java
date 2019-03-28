package com.nutritionalsupplements.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    private long id;

// ToDo
/*
Производитель
Страна производства
Состав
Пищевые Е-добавки
Срок и условия хранения
Пищевая и энергетическая ценность
    Белки
    Жиры
    Углеводы
    Калорийность
Стандарты
Штрих-код
*/
}