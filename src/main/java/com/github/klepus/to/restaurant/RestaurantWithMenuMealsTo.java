package com.github.klepus.to.restaurant;

import com.github.klepus.to.meal.MealWithPriceTo;

import java.util.Collections;
import java.util.List;

public class RestaurantWithMenuMealsTo extends RestaurantTo {

    private Integer menuId;

    private List<MealWithPriceTo> meals;

    public RestaurantWithMenuMealsTo() {
    }

    public RestaurantWithMenuMealsTo(int id, String name, Integer menuId, List<MealWithPriceTo> meals) {
        super(id, name);
        this.menuId = menuId;
        this.meals = Collections.unmodifiableList(meals);
    }

    public List<MealWithPriceTo> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "RestaurantWithMenuMealsTo{" +
                "menuId=" + menuId +
                ", meals=" + meals +
                "} " + super.toString();
    }
}
