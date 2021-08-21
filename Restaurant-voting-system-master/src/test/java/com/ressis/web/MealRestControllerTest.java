package com.ressis.web;

import com.ressis.TestUtil;
import com.ressis.model.Meal;
import com.ressis.service.meal.MealService;
import com.ressis.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static com.ressis.MealTestData.*;
import static com.ressis.TestUtil.*;
import static com.ressis.UserTestData.ADMIN;
import static com.ressis.UserTestData.USER1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + "/";

    @Autowired
    private MealService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform((get(REST_URL + MEAL1_ID)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform((get(REST_URL + MEAL1_ID)).with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Arrays.asList(MEAL2, MEAL6, MEAL5, MEAL4, MEAL1, MEAL3)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(service.getAll(), Arrays.asList(MEAL2, MEAL6, MEAL5, MEAL4, MEAL3));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setName("новое имя еды");

        mockMvc.perform(put(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(service.get(MEAL1_ID), updated);
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        Meal created = new Meal("ананас");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Meal actual = TestUtil.readFromJson(action, Meal.class);
        created.setId(actual.getId());

        assertMatch(actual, created);
        assertMatch(service.getAll(), Arrays.asList(created, MEAL2, MEAL6, MEAL5, MEAL4, MEAL1, MEAL3));
    }
}