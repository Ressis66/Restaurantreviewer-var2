package com.ressis.to.meal;

import com.ressis.model.Meal;
import com.ressis.to.BaseTo;

import java.util.Objects;

public class MealTo extends BaseTo {
    private String name;

    public MealTo() {
    }

    public MealTo(Meal meal) {
        this(meal.getId(), meal.getName());
    }

    public MealTo(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealTo mealTo = (MealTo) o;
        return Objects.equals(name, mealTo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
