package com.github.klepus.model;

import javax.persistence.*;

@Embeddable
public class MenuMeal {
    public MenuMeal() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @Column(name = "price")
    private double price;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
