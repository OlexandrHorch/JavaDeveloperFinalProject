package com.nutritionalsupplements.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "supplement")
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "e_cod")
    private String eCod;

    @Column(name = "name")
    private String name;

    @Column(name = "other_names", length = 2000)
    private String other_names;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    private SupplementCategory Category;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "danger")
    private SupplementDanger Danger;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "origin")
    private SupplementOrigin Origin;

    @Lob
    @Column(name = "using_info", length = 5000)
    private String using_info;

    @Column(name = "harm", length = 5000)
    private String harm;

    @Column(name = "benefit", length = 5000)
    private String benefit;

    @Column(name = "general_info", length = 5000)
    private String generalInfo;

    @Column(name = "legislation", length = 5000)
    private String legislation;
}
