package com.github.klepus.service.meal;


import com.github.klepus.model.Meal;
import com.github.klepus.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void update(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    List<Meal> getAll();
}
