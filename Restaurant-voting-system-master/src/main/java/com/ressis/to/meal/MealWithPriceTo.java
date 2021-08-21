package com.ressis.to.meal;

import java.util.Objects;

public class MealWithPriceTo extends MealTo {
    private int price;

    public MealWithPriceTo() {
    }

    public MealWithPriceTo(int id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MealWithPriceTo that = (MealWithPriceTo) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }

    @Override
    public String toString() {
        return "MealWithPriceTo{" +
                "price=" + price +
                "} " + super.toString();
    }
}
