package com.github.klepus.to;

import com.github.klepus.model.Meal;

import java.util.Objects;

public class MealTo {
    private final int id;
    private final String name;

    public MealTo(Meal meal) {
        this(meal.getId(), meal.getName());
    }

    public MealTo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealTo mealTO = (MealTo) o;
        return id == mealTO.id && Objects.equals(name, mealTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MealTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
