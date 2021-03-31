package com.github.klepus.repository.meal;


import com.github.klepus.model.Meal;

import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);

    // null if not found
    Meal get(int id);

    // ORDERED by name asc
    List<Meal> getAll();

    // false if not found
    boolean delete(int id);
}
