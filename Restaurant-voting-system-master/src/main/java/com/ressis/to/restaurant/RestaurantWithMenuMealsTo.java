package com.ressis.to.restaurant;

import com.ressis.to.meal.MealWithPriceTo;

import java.util.Collections;
import java.util.List;

public class RestaurantWithMenuMealsTo extends RestaurantTo {

    private Integer menuId;

    private List<MealWithPriceTo> meals;

    public RestaurantWithMenuMealsTo() {
    }

    public RestaurantWithMenuMealsTo(int id, String name, Integer menuId, List<MealWithPriceTo> meals) {
        super(id, name);
        this.menuId = menuId;
        this.meals = Collections.unmodifiableList(meals);
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public List<MealWithPriceTo> getMeals() {
        return meals;
    }

    public void setMeals(List<MealWithPriceTo> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "RestaurantWithMenuMealsTo{" +
                "menuId=" + menuId +
                ", meals=" + meals +
                "} " + super.toString();
    }
}
