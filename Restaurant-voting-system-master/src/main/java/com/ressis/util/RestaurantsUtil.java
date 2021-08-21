package com.ressis.util;

import com.ressis.model.Menu;
import com.ressis.model.Restaurant;
import com.ressis.to.restaurant.RestaurantTo;
import com.ressis.to.restaurant.RestaurantWithMenuMealsTo;
import com.ressis.to.restaurant.RestaurantWithVoteTo;
import com.ressis.to.meal.MealWithPriceTo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantsUtil {

    public static RestaurantTo createFromEntity(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> createFromEntity(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> new RestaurantTo(restaurant.getId(), restaurant.getName())).collect(Collectors.toList());
    }

    public static RestaurantWithMenuMealsTo createFromEntityWithMeals(Restaurant restaurant) {
        int menusSize = restaurant.getMenus().size();
        Integer menuId = null;
        if (menusSize == 1) {
            Menu menu = restaurant.getMenus().iterator().next();
            menuId = menu.getId();
        } else if (menusSize > 1) {
            throw new IllegalArgumentException(restaurant + " should contain only one menu");
        }
        List<MealWithPriceTo> mealList = restaurant.getMenus().stream()
                .flatMap(m -> m.getMenuMeals().stream())
                .map(m -> new MealWithPriceTo(m.getMeal().getId(), m.getMeal().getName(), m.getPrice()))
                .collect(Collectors.toList());
        return new RestaurantWithMenuMealsTo(restaurant.getId(), restaurant.getName(), menuId, mealList);
    }

    public static List<RestaurantWithMenuMealsTo> createFromEntityWithMeals(List<Restaurant> restaurant) {
        return restaurant.stream().map(RestaurantsUtil::createFromEntityWithMeals).collect(Collectors.toList());
    }

    public static RestaurantWithVoteTo createFromEntityWithVote(Restaurant restaurant) {
        int voteCount = restaurant.getMenus().stream().map(Menu::getVotes).mapToInt(Set::size).sum();
        return new RestaurantWithVoteTo(restaurant.getId(), restaurant.getName(), voteCount);
    }

    public static List<RestaurantWithVoteTo> createFromEntityWithVote(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantsUtil::createFromEntityWithVote).collect(Collectors.toList());
    }
}
