package com.github.klepus.util;


import com.github.klepus.model.Menu;
import com.github.klepus.model.Restaurant;
import com.github.klepus.to.MealWithPriceTo;
import com.github.klepus.to.RestaurantT0;
import com.github.klepus.to.RestaurantWithMenuMealsT0;
import com.github.klepus.to.RestaurantWithVoteT0;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantsUtil {

    public static RestaurantT0 create(Restaurant restaurant) {
        return new RestaurantT0(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantT0> create(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> new RestaurantT0(restaurant.getId(), restaurant.getName())).collect(Collectors.toList());
    }

    public static RestaurantWithMenuMealsT0 createWithMeals(Restaurant restaurant) {
        List<MealWithPriceTo> mealList = restaurant.getMenus().stream()
                .flatMap(menu -> menu.getMenuMeals().stream())
                .map(menuMeal -> new MealWithPriceTo(menuMeal.getMeal().getId(), menuMeal.getMeal().getName(), menuMeal.getPrice()))
                .collect(Collectors.toList());
        return new RestaurantWithMenuMealsT0(restaurant.getId(), restaurant.getName(), mealList);
    }

    public static List<RestaurantWithMenuMealsT0> createWithMeals(List<Restaurant> restaurant) {
        return restaurant.stream().map(RestaurantsUtil::createWithMeals).collect(Collectors.toList());
    }

    public static RestaurantWithVoteT0 createWithVote(Restaurant restaurant) {
        int voteCount = restaurant.getMenus().stream().map(Menu::getVotes).mapToInt(Set::size).sum();
        return new RestaurantWithVoteT0(restaurant.getId(), restaurant.getName(), voteCount);
    }

    public static List<RestaurantWithVoteT0> createWithVote(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantsUtil::createWithVote).collect(Collectors.toList());
    }
}
