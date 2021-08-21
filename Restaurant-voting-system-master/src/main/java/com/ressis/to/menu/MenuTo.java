package com.ressis.to.menu;

import com.ressis.to.BaseTo;
import com.ressis.to.meal.MealWithPriceTo;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class MenuTo extends BaseTo {

    private int restaurantId;

    private LocalDate date;

    private Set<MealWithPriceTo> meals;

    public MenuTo() {
    }

    public MenuTo(int id) {
        super(id);
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<MealWithPriceTo> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealWithPriceTo> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MenuTo menuTo = (MenuTo) o;
        return restaurantId == menuTo.restaurantId &&
                Objects.equals(date, menuTo.date) &&
                Objects.equals(meals, menuTo.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), restaurantId, date, meals);
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "restaurantId=" + restaurantId +
                ", date=" + date +
                ", meals=" + meals +
                "} " + super.toString();
    }
}
