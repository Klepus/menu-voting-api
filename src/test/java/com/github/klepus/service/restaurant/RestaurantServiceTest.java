package com.github.klepus.service.restaurant;

import com.github.klepus.model.Restaurant;
import com.github.klepus.service.AbstractServiceTest;
import com.github.klepus.to.restaurant.RestaurantTo;
import com.github.klepus.to.restaurant.RestaurantWithMenuMealsTo;
import com.github.klepus.to.restaurant.RestaurantWithVoteTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.klepus.RestaurantTestData.*;
import static com.github.klepus.TestUtil.assertMatch;
import static com.github.klepus.TestUtil.assertMatchInAnyOrder;

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
        assertMatch(service.getAll(), Arrays.asList(RESTAURANT1_TO, RESTAURANT2_TO, RESTAURANT3_TO));
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
        RestaurantWithMenuMealsTo restaurant_id1 = allWithMenusOnDate.get(0);
        RestaurantWithMenuMealsTo restaurant_id2 = allWithMenusOnDate.get(1);
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
        assertMatch(allWithVotesOnDate, Arrays.asList(RESTAURANT1_VOTE, RESTAURANT2_VOTE, RESTAURANT3_VOTE));
    }
}