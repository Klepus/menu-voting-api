package com.github.klepus.service.meal;

import com.github.klepus.model.Meal;
import com.github.klepus.service.AbstractServiceTest;
import com.github.klepus.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.github.klepus.MealTestData.*;
import static com.github.klepus.TestUtil.assertMatch;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testCreate() {
        Meal created = service.create(new Meal("ааа"));
        assertMatch(created, service.get(created.getId()));
        assertMatch(service.getAll(), Arrays.asList(created, MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6));
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
        assertMatch(service.getAll(), Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6));
    }

    @Test
    public void testDelete() {
        service.delete(MEAL1_ID);
        assertMatch(service.getAll(), Arrays.asList(MEAL2, MEAL3, MEAL4, MEAL5, MEAL6));
    }

    @Test
    public void testDeleteNotExisting() {
        thrown.expect(NotFoundException.class);
        service.delete(-1);
    }
}