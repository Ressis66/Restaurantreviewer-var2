package com.ressis.service.meal;

import com.ressis.model.Meal;
import com.ressis.service.AbstractServiceTest;
import com.ressis.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.ressis.MealTestData.*;
import static com.ressis.TestUtil.assertMatch;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testCreate() {
        Meal created = service.create(new Meal("ааа"));
        assertMatch(created, service.get(created.getId()));
        assertMatch(service.getAll(), Arrays.asList(created, MEAL2, MEAL6, MEAL5, MEAL4, MEAL1, MEAL3));
    }

    @Test
    public void testUpdate() {
        Meal updated = new Meal(MEAL1);
        updated.setName("новое имя блюда");
        service.update(updated);

        assertMatch(service.get(MEAL1_ID), updated);
    }

    @Test
    public void testGet() {
        assertMatch(service.get(MEAL1_ID), MEAL1);
    }

    @Test
    public void testGetNotExisting() {
        thrown.expect(NotFoundException.class);
        service.get(-1);
    }

    @Test
    public void testGetAll() {
        assertMatch(service.getAll(), Arrays.asList(MEAL2, MEAL6, MEAL5, MEAL4, MEAL1, MEAL3));
    }

    @Test
    public void testDelete() {
        service.delete(MEAL1_ID);
        assertMatch(service.getAll(), Arrays.asList(MEAL2, MEAL6, MEAL5, MEAL4, MEAL3));
    }

    @Test
    public void testDeleteNotExisting() {
        thrown.expect(NotFoundException.class);
        service.delete(-1);
    }
}