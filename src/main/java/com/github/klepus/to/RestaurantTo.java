package com.github.klepus.to;

public class RestaurantTo {
    private final int id;
    private final String name;

    public RestaurantTo(int id, String name) {
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
    public String toString() {
        return "RestaurantTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
