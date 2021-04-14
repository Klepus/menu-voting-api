package com.github.klepus;

import com.github.klepus.model.Restaurant;
import com.github.klepus.to.meal.MealWithPriceTo;
import com.github.klepus.to.restaurant.RestaurantTo;
import com.github.klepus.to.restaurant.RestaurantWithVoteTo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.github.klepus.MealTestData.*;
import static com.github.klepus.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID1 = START_SEQ + 10;
    public static final int RESTAURANT_ID2 = START_SEQ + 11;
    public static final int RESTAURANT_ID3 = START_SEQ + 12;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "ресторан_1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "ресторан_2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID3, "ресторан_3");

    public static Restaurant getForCreation() {
        return new Restaurant("new_restaurant");
    }

    public static Restaurant getForUpdating() {
        return new Restaurant(RESTAURANT_ID1, "new_restaurant");
    }


    public static final RestaurantTo RESTAURANT1_TO = new RestaurantTo(RESTAURANT_ID1, "ресторан_1");
    public static final RestaurantTo RESTAURANT2_TO = new RestaurantTo(RESTAURANT_ID2, "ресторан_2");
    public static final RestaurantTo RESTAURANT3_TO = new RestaurantTo(RESTAURANT_ID3, "ресторан_3");

    public static final LocalDate DATE = LocalDate.of(2021, 1, 1);
    public static final String DATE_STR = DATE.format(DateTimeFormatter.ISO_LOCAL_DATE);

    public static final MealWithPriceTo RESTAURANT1_MEAL1 = new MealWithPriceTo(MEAL2.getId(), MEAL2.getName(), 150);
    public static final MealWithPriceTo RESTAURANT1_MEAL2 = new MealWithPriceTo(MEAL1.getId(), MEAL1.getName(), 100);

    public static final MealWithPriceTo RESTAURANT2_MEAL1 = new MealWithPriceTo(MEAL5.getId(), MEAL5.getName(), 125);
    public static final MealWithPriceTo RESTAURANT2_MEAL2 = new MealWithPriceTo(MEAL1.getId(), MEAL1.getName(), 250);

    public static final RestaurantWithVoteTo RESTAURANT1_VOTE =
            new RestaurantWithVoteTo(RESTAURANT_ID1, RESTAURANT1.getName(), 1);

    public static final RestaurantWithVoteTo RESTAURANT2_VOTE =
            new RestaurantWithVoteTo(RESTAURANT_ID2, RESTAURANT2.getName(), 2);

    public static final RestaurantWithVoteTo RESTAURANT3_VOTE =
            new RestaurantWithVoteTo(RESTAURANT_ID3, RESTAURANT3.getName(), 0);
}
