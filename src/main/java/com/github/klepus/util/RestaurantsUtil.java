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

    public static RestaurantTo create(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> create(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> new RestaurantTo(restaurant.getId(), restaurant.getName())).collect(Collectors.toList());
    }

    public static RestaurantWithMenuMealsTo createWithMeals(Restaurant restaurant) {
        List<MealWithPriceTo> mealList = restaurant.getMenus().stream()
                .flatMap(menu -> menu.getMenuMeals().stream())
                .map(menuMeal -> new MealWithPriceTo(menuMeal.getMeal().getId(), menuMeal.getMeal().getName(), menuMeal.getPrice()))
                .collect(Collectors.toList());
        return new RestaurantWithMenuMealsTo(restaurant.getId(), restaurant.getName(), mealList);
    }

    public static List<RestaurantWithMenuMealsTo> createWithMeals(List<Restaurant> restaurant) {
        return restaurant.stream().map(RestaurantsUtil::createWithMeals).collect(Collectors.toList());
    }

    public static RestaurantWithVoteTo createWithVote(Restaurant restaurant) {
        int voteCount = restaurant.getMenus().stream().map(Menu::getVotes).mapToInt(Set::size).sum();
        return new RestaurantWithVoteTo(restaurant.getId(), restaurant.getName(), voteCount);
    }

    public static List<RestaurantWithVoteTo> createWithVote(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantsUtil::createWithVote).collect(Collectors.toList());
    }
}
