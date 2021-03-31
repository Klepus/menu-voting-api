package com.github.klepus.repository.restaurant;


import com.github.klepus.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    // null if not exist
    Restaurant get(int id);

    // null if not exist
    Restaurant getOneWithMenusBetweenDates(int id, LocalDate startDate, LocalDate endDate);

    default Restaurant getOneWithMenusOnDate(int id, LocalDate date) {
        return getOneWithMenusBetweenDates(id, date, date);
    }

    // null if not exist
    Restaurant getOneWithVotesBetweenDates(int id, LocalDate startDate, LocalDate endDate);

    default Restaurant getOneWithVotesOnDate(int id, LocalDate date) {
        return getOneWithVotesBetweenDates(id, date, date);
    }

    // ORDERED by name asc
    List<Restaurant> getAll();

    // ORDERED by name asc
    List<Restaurant> getAllWithMenusBetweenDates(LocalDate startDate, LocalDate endDate);

    default List<Restaurant> getAllWithMenusOnDate(LocalDate date) {
        return getAllWithMenusBetweenDates(date, date);
    }

    // ORDERED by name asc
    List<Restaurant> getAllWithVotesBetweenDates(LocalDate startDate, LocalDate endDate);

    default List<Restaurant> getAllWithVotesOnDate(LocalDate date) {
        return getAllWithVotesBetweenDates(date, date);
    }
}
