package com.ressis.service.restaurant;

import com.ressis.model.Restaurant;
import com.ressis.to.restaurant.RestaurantTo;
import com.ressis.to.restaurant.RestaurantWithMenuMealsTo;
import com.ressis.to.restaurant.RestaurantWithVoteTo;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    // null if not exist
    RestaurantTo get(int id);

    // null if not exist
    RestaurantWithMenuMealsTo getOneWithMenuOnDate(int id, LocalDate date);

    // null if not exist
    RestaurantWithVoteTo getOneWithVoteOnDate(int id, LocalDate date);

    // ORDERED by name asc
    List<RestaurantTo> getAll();

    // ORDERED by name asc
    List<RestaurantWithMenuMealsTo> getAllWithMenusOnDate(LocalDate date);

    // ORDERED by name asc
    List<RestaurantWithVoteTo> getAllWithVotesOnDate(LocalDate date);
}
