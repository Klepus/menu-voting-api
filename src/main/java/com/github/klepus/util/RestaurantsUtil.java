package com.github.klepus.util;


import com.github.klepus.model.Menu;
import com.github.klepus.model.Restaurant;
import com.github.klepus.to.MealWithPriceTo;
import com.github.klepus.to.RestaurantTo;
import com.github.klepus.to.RestaurantWithMenuMealsTo;
import com.github.klepus.to.RestaurantWithVoteTo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantsUtil {

    public static RestaurantTo createFromEntity(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> createFromEntity(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> new RestaurantTo(restaurant.getId(), restaurant.getName())).collect(Collectors.toList());
    }

    public static RestaurantWithMenuMealsTo createFromEntityWithMeals(Restaurant restaurant) {
        List<MealWithPriceTo> mealList = restaurant.getMenus().stream()
                .flatMap(menu -> menu.getMenuMeals().stream())
                .map(menuMeal -> new MealWithPriceTo(menuMeal.getMeal().getId(), menuMeal.getMeal().getName(), menuMeal.getPrice()))
                .collect(Collectors.toList());
        return new RestaurantWithMenuMealsTo(restaurant.getId(), restaurant.getName(), mealList);
    }

    public static List<RestaurantWithMenuMealsTo> createFromEntityWithMeals(List<Restaurant> restaurant) {
        return restaurant.stream().map(RestaurantsUtil::createFromEntityWithMeals).collect(Collectors.toList());
    }

    public static RestaurantWithVoteTo createFromEntityWithVote(Restaurant restaurant) {
        int voteCount = restaurant.getMenus().stream().map(Menu::getVotes).mapToInt(Set::size).sum();
        return new RestaurantWithVoteTo(restaurant.getId(), restaurant.getName(), voteCount);
    }

    public static List<RestaurantWithVoteTo> createFromEntityWithVote(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantsUtil::createFromEntityWithVote).collect(Collectors.toList());
    }
}
