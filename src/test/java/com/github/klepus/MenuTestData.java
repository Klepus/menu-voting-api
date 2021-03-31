package com.github.klepus;

import com.github.klepus.to.meal.MealWithPriceTo;
import com.github.klepus.to.menu.MenuTo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.github.klepus.RestaurantTestData.DATE;
import static com.github.klepus.UserTestData.USER1_ID;

public class MenuTestData {

    public static final int UPDATE_MENU_ID = 100013;

    public static final int MENU_ID_FROM_VOTE = 100015;

    public static final int USER_VOTING_ID = USER1_ID;

    public static MenuTo getForCreation(LocalDate date, int restaurantId) {
        MenuTo menuTo = new MenuTo();
        menuTo.setDate(date);
        menuTo.setRestaurantId(restaurantId);

        Set<MealWithPriceTo> mealWithPrice = new HashSet<>();
        mealWithPrice.add(new MealWithPriceTo(MealTestData.MEAL1_ID, MealTestData.MEAL1.getName(), 100));
        mealWithPrice.add(new MealWithPriceTo(MealTestData.MEAL2_ID, MealTestData.MEAL2.getName(), 130));

        menuTo.setMeals(mealWithPrice);
        return menuTo;
    }

    public static MenuTo getForUpdating(int restaurantId) {
        MenuTo menuTo = new MenuTo(UPDATE_MENU_ID);
        menuTo.setRestaurantId(restaurantId);
        menuTo.setDate(DATE);

        Set<MealWithPriceTo> mealWithPrice = new HashSet<>();
        mealWithPrice.add(new MealWithPriceTo(MealTestData.MEAL1_ID, MealTestData.MEAL1.getName(), 777));
        menuTo.setMeals(mealWithPrice);
        return menuTo;
    }

}
