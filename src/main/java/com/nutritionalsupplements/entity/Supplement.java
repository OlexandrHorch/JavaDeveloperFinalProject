package com.nutritionalsupplements.entity;

import javax.persistence.*;

@Entity
@Table(name = "supplements")
public class Supplement {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Category")
    private SupplementEnumCategory Category = SupplementEnumCategory.ANTIOXIDANTS;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Danger")
    private SupplementEnumDanger Danger = SupplementEnumDanger.VERY_LOW;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Origin")
    private SupplementEnumOrigin Origin = SupplementEnumOrigin.ANIMAL;

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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SupplementEnumCategory getCategory() {
        return Category;
    }

    public void setCategory(SupplementEnumCategory category) {
        Category = category;
    }

    public SupplementEnumDanger getDanger() {
        return Danger;
    }

    public void setDanger(SupplementEnumDanger danger) {
        Danger = danger;
    }

    public SupplementEnumOrigin getOrigin() {
        return Origin;
    }

    public void setOrigin(SupplementEnumOrigin origin) {
        Origin = origin;
    }

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getHarm() {
        return harm;
    }

    public void setHarm(String harm) {
        this.harm = harm;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }

    public String getLegislation() {
        return legislation;
    }

    public void setLegislation(String legislation) {
        this.legislation = legislation;
    }
}