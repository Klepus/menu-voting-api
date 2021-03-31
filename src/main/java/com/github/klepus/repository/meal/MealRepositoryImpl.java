package com.github.klepus.repository.meal;

import com.github.klepus.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MealRepositoryImpl implements MealRepository {

    private static final Sort SORT_BY_NAME = Sort.by(Sort.Direction.ASC, "name");

    @Autowired
    private CrudMealRepository repository;

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public Meal get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return repository.findAll(SORT_BY_NAME);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }
}
