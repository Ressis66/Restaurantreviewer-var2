package com.ressis.service.restaurant;

import com.ressis.model.Restaurant;
import com.ressis.service.AbstractServiceTest;
import com.ressis.to.restaurant.RestaurantTo;
import com.ressis.to.restaurant.RestaurantWithMenuMealsTo;
import com.ressis.to.restaurant.RestaurantWithVoteTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ressis.RestaurantTestData.*;
import static com.ressis.TestUtil.assertMatch;
import static com.ressis.TestUtil.assertMatchInAnyOrder;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testCreate() {
        Restaurant expected = getForCreation();
        Restaurant actual = service.create(expected);
        expected.setId(actual.getId());
        assertMatch(actual, expected);
    }

    @Test
    public void testUpdate() {
        Restaurant expected = getForUpdating();
        service.update(expected);
        assertMatch(service.get(expected.getId()), expected);
    }

    @Test
    public void testGet() {
        assertMatch(service.get(RESTAURANT_ID1), RESTAURANT1_TO);
    }

    @Test
    public void testGetAll() {
        List<RestaurantTo> all = service.getAll();
        assertMatch(service.getAll(), Arrays.asList(RESTAURANT2_TO, RESTAURANT1_TO, RESTAURANT3_TO));
    }

    @Test
    public void testGetOneWithMenuOnDate() {
        RestaurantWithMenuMealsTo oneWithMenuOnDate = service.getOneWithMenuOnDate(RESTAURANT_ID2, DATE);
        assertMatch(oneWithMenuOnDate, RESTAURANT2_TO, "meals", "menuId");
        assertMatchInAnyOrder(oneWithMenuOnDate.getMeals(), RESTAURANT2_MEAL1, RESTAURANT2_MEAL2);
    }

    @Test
    public void testGetOneWithVoteOnDate() {
        assertMatch(service.getOneWithVoteOnDate(RESTAURANT_ID2, DATE), RESTAURANT2_VOTE);
    }

    @Test
    public void testGetAllWithMenusOnDate() {
        List<RestaurantWithMenuMealsTo> allWithMenusOnDate = service.getAllWithMenusOnDate(DATE);
        RestaurantWithMenuMealsTo restaurant_id2 = allWithMenusOnDate.get(0);
        RestaurantWithMenuMealsTo restaurant_id1 = allWithMenusOnDate.get(1);
        RestaurantWithMenuMealsTo restaurant_id3 = allWithMenusOnDate.get(2);

        assertMatch(restaurant_id2, RESTAURANT2_TO, "meals", "menuId");
        assertMatchInAnyOrder(restaurant_id2.getMeals(), RESTAURANT2_MEAL1, RESTAURANT2_MEAL2);

        assertMatch(restaurant_id1, RESTAURANT1_TO, "meals", "menuId");
        assertMatchInAnyOrder(restaurant_id1.getMeals(), RESTAURANT1_MEAL1, RESTAURANT1_MEAL2);

        assertMatch(restaurant_id3, RESTAURANT3_TO, "meals", "menuId");
        assertMatch(restaurant_id3.getMeals(), new ArrayList<>());
    }

    @Test
    public void testGetAllWithVotesOnDate() {
        List<RestaurantWithVoteTo> allWithVotesOnDate = service.getAllWithVotesOnDate(DATE);
        assertMatch(allWithVotesOnDate, Arrays.asList(RESTAURANT2_VOTE, RESTAURANT1_VOTE, RESTAURANT3_VOTE));
    }
}