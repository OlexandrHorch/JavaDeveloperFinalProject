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

    public SupplementCategory getCategory() {
        return Category;
    }

    public void setCategory(SupplementCategory category) {
        Category = category;
    }

    public SupplementDanger getDanger() {
        return Danger;
    }

    public void setDanger(SupplementDanger danger) {
        Danger = danger;
    }

    public SupplementOrigin getOrigin() {
        return Origin;
    }

    public void setOrigin(SupplementOrigin origin) {
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