package com.ressis.service.menu;

import com.ressis.service.AbstractServiceTest;
import com.ressis.service.restaurant.RestaurantService;
import com.ressis.to.menu.MenuTo;
import com.ressis.to.restaurant.RestaurantWithMenuMealsTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ressis.MenuTestData.*;
import static com.ressis.MenuTestData.getForCreation;
import static com.ressis.MenuTestData.getForUpdating;
import static com.ressis.RestaurantTestData.*;
import static com.ressis.TestUtil.*;

public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void testCreate() {
        MenuTo menuTo = getForCreation(DATE, RESTAURANT_ID3);
        menuTo.setId(menuService.create(menuTo).getId());

        RestaurantWithMenuMealsTo restaurant = restaurantService.getOneWithMenuOnDate(RESTAURANT_ID3, DATE);

        assertMatch(restaurant.getMenuId(), menuTo.getId());
        assertMatchInAnyOrder(restaurant.getMeals(), getMealArrayFromCollection(menuTo.getMeals()));
    }

    @Test
    public void testUpdate() {
        MenuTo menuTo = getForUpdating(RESTAURANT_ID1);
        menuService.update(menuTo);

        RestaurantWithMenuMealsTo restaurant = restaurantService.getOneWithMenuOnDate(RESTAURANT_ID1, DATE);

        assertMatch(restaurant.getId(), menuTo.getRestaurantId());
        assertMatch(restaurant.getMenuId(), menuTo.getId());
        assertMatchInAnyOrder(restaurant.getMeals(), getMealArrayFromCollection(menuTo.getMeals()));
    }

    @Test
    public void testMakeVote() {
        menuService.makeVote(MENU_ID_FROM_VOTE, USER_VOTING_ID, DATE);

        assertMatch(restaurantService.getOneWithVoteOnDate(RESTAURANT_ID1, DATE).getVoteCount(), 0);
        assertMatch(restaurantService.getOneWithVoteOnDate(RESTAURANT_ID2, DATE).getVoteCount(), 3);
    }
}