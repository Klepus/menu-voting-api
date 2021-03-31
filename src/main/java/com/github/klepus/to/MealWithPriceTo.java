package com.github.klepus.to;

public class MealWithPriceTo extends MealTo {
    private final int price;

    public MealWithPriceTo(int id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MealWithPriceTO{" +
                "price=" + price +
                "} " + super.toString();
    }
}
