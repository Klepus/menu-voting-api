package com.github.klepus.repository.meal;

import com.github.klepus.model.Meal;
import com.github.klepus.repository.AbstractRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.github.klepus.MealTestData.*;
import static com.github.klepus.TestUtil.assertMatch;
import static org.assertj.core.api.Assertions.assertThat;

public class MealRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private MealRepository repository;

    @Test
    public void testCreate() {
        Meal created = repository.save(new Meal("ааа"));
        assertMatch(created, repository.get(created.getId()));
        assertMatch(repository.getAll(), Arrays.asList(created, MEAL2, MEAL6, MEAL5, MEAL4, MEAL1, MEAL3));
    }

    @Test
    public void testUpdate() {
        Meal updated = new Meal(MEAL1);
        updated.setName("новое имя блюда");
        Meal actualUpdated = repository.save(updated);

        assertMatch(actualUpdated, updated);
    }

    @Test
    public void testGet() {
        assertMatch(repository.get(MEAL1_ID), MEAL1);
    }

    @Test
    public void testGetNotExisting() {
        assertThat(repository.get(-1)).isNull();
    }

    @Test
    public void testGetAll() {
        assertMatch(repository.getAll(), Arrays.asList(MEAL2, MEAL6, MEAL5, MEAL4, MEAL1, MEAL3));
    }

    @Test
    public void testDelete() {
        assertThat(repository.delete(MEAL1_ID)).isEqualTo(true);
        assertMatch(repository.getAll(), Arrays.asList(MEAL2, MEAL6, MEAL5, MEAL4, MEAL3));
    }

    @Test
    public void testDeleteNotExisting() {
        assertThat(repository.delete(-1)).isEqualTo(false);
    }
}