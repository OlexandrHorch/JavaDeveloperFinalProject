package com.nutritionalsupplements.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplements")
public class Supplement {

    @Id
    private long id;

}