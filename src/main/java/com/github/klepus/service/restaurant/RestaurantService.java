package com.github.klepus.service.restaurant;

import com.github.klepus.model.Restaurant;
import com.github.klepus.to.restaurant.RestaurantTo;
import com.github.klepus.to.restaurant.RestaurantWithMenuMealsTo;
import com.github.klepus.to.restaurant.RestaurantWithVoteTo;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    // null if not exist
    RestaurantTo get(int id);

    // null if not exist
    RestaurantWithMenuMealsTo getOneWithMenuOnDate(int id, LocalDate date);

    // null if not exist
    RestaurantWithVoteTo getOneWithVoteOnDate(int id, LocalDate date);

    // ORDERED by name asc
    List<RestaurantTo> getAll();

    // ORDERED by name asc
    List<RestaurantWithMenuMealsTo> getAllWithMenusOnDate(LocalDate date);

    // ORDERED by name asc
    List<RestaurantWithVoteTo> getAllWithVotesOnDate(LocalDate date);
}
