package com.ressis.web;

import com.ressis.MenuTestData;
import com.ressis.RestaurantTestData;
import com.ressis.TestUtil;
import com.ressis.model.Restaurant;
import com.ressis.service.restaurant.RestaurantService;
import com.ressis.to.menu.MenuTo;
import com.ressis.to.restaurant.RestaurantWithMenuMealsTo;
import com.ressis.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.ressis.RestaurantTestData.*;
import static com.ressis.TestUtil.*;
import static com.ressis.UserTestData.ADMIN;
import static com.ressis.UserTestData.USER1;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID1).with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1_TO));
    }

    @Test
    public void testGetOneWithMeals() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID1 + "/menu").param("date", DATE_STR).with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getOneWithMenuOnDate(RESTAURANT_ID1, DATE)));
    }

    @Test
    public void testGetOneWithVote() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID1 + "/vote").param("date", DATE_STR).with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getOneWithVoteOnDate(RESTAURANT_ID1, DATE)));
    }

    @Test
    public void testGetAllWithMeals() throws Exception {
        mockMvc.perform(get(REST_URL + "menu").param("date", DATE_STR).with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getAllWithMenusOnDate(DATE)));
    }

    @Test
    public void testGetAllWithVote() throws Exception {
        mockMvc.perform(get(REST_URL + "vote").param("date", DATE_STR).with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getAllWithVotesOnDate(DATE)));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getAll()));
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant expected = RestaurantTestData.getForUpdating();
        mockMvc.perform(put(REST_URL + expected.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(service.get(expected.getId()), expected);
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        Restaurant expected = RestaurantTestData.getForCreation();
        ResultActions resultActions = mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());

        Restaurant actual = TestUtil.readFromJson(resultActions, Restaurant.class);
        expected.setId(actual.getId());
        assertMatch(actual, expected);
    }

    @Test
    public void testCreateMenu() throws Exception {
        MenuTo expected = MenuTestData.getForCreation(DATE, RESTAURANT_ID3);
        mockMvc.perform(post(REST_URL + RESTAURANT_ID3 + "/menu").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());

        RestaurantWithMenuMealsTo restaurant = service.getOneWithMenuOnDate(RESTAURANT_ID3, DATE);
        assertMatchInAnyOrder(restaurant.getMeals(), getMealArrayFromCollection(expected.getMeals()));
    }

    @Test
    public void testUpdateMenu() throws Exception {
        MenuTo expected = MenuTestData.getForUpdating(RESTAURANT_ID3);
        mockMvc.perform(put(REST_URL + RESTAURANT_ID3 + "/menu").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());

        RestaurantWithMenuMealsTo restaurant = service.getOneWithMenuOnDate(RESTAURANT_ID3, DATE);
        assertMatchInAnyOrder(restaurant.getMeals(), getMealArrayFromCollection(expected.getMeals()));
    }
}