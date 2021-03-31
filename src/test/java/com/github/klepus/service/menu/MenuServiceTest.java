package com.github.klepus.service.menu;

import com.github.klepus.service.AbstractServiceTest;
import com.github.klepus.service.restaurant.RestaurantService;
import com.github.klepus.to.menu.MenuTo;
import com.github.klepus.to.restaurant.RestaurantWithMenuMealsTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.klepus.MenuTestData.*;
import static com.github.klepus.RestaurantTestData.*;
import static com.github.klepus.TestUtil.assertMatch;


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
        assertMatch(restaurant.getMeals(), menuTo.getMeals());
    }

    @Test
    public void testUpdate() {
        MenuTo menuTo = getForUpdating(RESTAURANT_ID1);
        menuService.update(menuTo);

        RestaurantWithMenuMealsTo restaurant = restaurantService.getOneWithMenuOnDate(RESTAURANT_ID1, DATE);

        assertMatch(restaurant.getId(), menuTo.getRestaurantId());
        assertMatch(restaurant.getMenuId(), menuTo.getId());
        assertMatch(restaurant.getMeals(), menuTo.getMeals());
    }

    @Test
    public void testMakeVote() {
        menuService.makeVote(MENU_ID_FROM_VOTE, USER_VOTING_ID, DATE);

        assertMatch(restaurantService.getOneWithVoteOnDate(RESTAURANT_ID1, DATE).getVoteCount(), 0);
        assertMatch(restaurantService.getOneWithVoteOnDate(RESTAURANT_ID2, DATE).getVoteCount(), 3);
    }
}