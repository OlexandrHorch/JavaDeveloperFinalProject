package com.additives.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "additive")
public class Additive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "e_cod")
    private String eCod;

    @Column(name = "name")
    private String name;

    @Column(name = "other_names", length = 2000)
    private String otherNames;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    private AdditiveCategory category;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "danger")
    private AdditiveDanger danger;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "origin")
    private AdditiveOrigin origin;

    @Lob
    @Column(name = "using_info", length = 5000)
    private String usingInfo;

    @Column(name = "harm", length = 5000)
    private String harm;

    @Column(name = "benefit", length = 5000)
    private String benefit;

    @Column(name = "general_info", length = 5000)
    private String generalInfo;

    @Column(name = "legislation", length = 5000)
    private String legislation;
}