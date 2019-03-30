package com.nutritionalsupplements.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "supplements")
public class Supplement {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    private SupplementCategory Category;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "danger")
    private SupplementDanger Danger;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "origin")
    private SupplementOrigin Origin;

    @Column(name = "using")
    private String using;

    @Column(name = "harm")
    private String harm;

    @Column(name = "benefit")
    private String benefit;

    @Column(name = "general_info")
    private String generalInfo;

    @Column(name = "legislation")
    private String legislation;
}