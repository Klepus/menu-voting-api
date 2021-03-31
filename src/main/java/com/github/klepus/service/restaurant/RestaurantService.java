package com.github.klepus.service.restaurant;

import com.github.klepus.model.Restaurant;
import com.github.klepus.to.RestaurantT0;
import com.github.klepus.to.RestaurantWithMenuMealsT0;
import com.github.klepus.to.RestaurantWithVoteT0;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    // null if not exist
    RestaurantT0 get(int id);

    // null if not exist
    RestaurantWithMenuMealsT0 getOneWithMenuOnDate(int id, LocalDate date);

    // null if not exist
    RestaurantWithVoteT0 getOneWithVoteOnDate(int id, LocalDate date);

    // ORDERED by name asc
    List<RestaurantT0> getAll();

    // ORDERED by name asc
    List<RestaurantWithMenuMealsT0> getAllWithMenusOnDate(LocalDate date);

    // ORDERED by name asc
    List<RestaurantWithVoteT0> getAllWithVotesOnDate(LocalDate date);
}
