package com.ressis.model;

import javax.persistence.*;

@Embeddable
public class MenuMeal {
    public MenuMeal() {
    }

    public MenuMeal(Meal meal, int price) {
        this.meal = meal;
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @Column(name = "price")
    private int price;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
