package com.github.klepus.web.controller;

import com.github.klepus.AuthorizedUser;
import com.github.klepus.model.Restaurant;
import com.github.klepus.service.menu.MenuService;
import com.github.klepus.service.restaurant.RestaurantService;
import com.github.klepus.to.menu.MenuTo;
import com.github.klepus.to.restaurant.RestaurantTo;
import com.github.klepus.to.restaurant.RestaurantWithMenuMealsTo;
import com.github.klepus.to.restaurant.RestaurantWithVoteTo;
import com.github.klepus.util.exception.VotingException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.github.klepus.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(path = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_URL = "/rest/restaurant";

    private static final LocalTime VOTE_ACCEPTED_TIME_TO = LocalTime.of(11, 0, 0);

    private final RestaurantService restaurantService;

    private final MenuService menuService;

    public RestaurantRestController(RestaurantService restaurantService, MenuService menuService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }

    @GetMapping(path = "/{id}")
    public RestaurantTo get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @GetMapping(path = "/{id}/menu")
    public RestaurantWithMenuMealsTo getWithMeals(@PathVariable("id") int id, @RequestParam("date") LocalDate date) {
        return restaurantService.getOneWithMenuOnDate(id, date);
    }

    @GetMapping(path = "/{id}/vote")
    public RestaurantWithVoteTo getWithVote(@PathVariable("id") int id, @RequestParam("date") LocalDate date) {
        return restaurantService.getOneWithVoteOnDate(id, date);
    }

    @GetMapping(path = "/menu")
    public List<RestaurantWithMenuMealsTo> getWithMeals(@RequestParam("date") LocalDate date) {
        return restaurantService.getAllWithMenusOnDate(date);
    }

    @GetMapping(path = "/vote")
    public List<RestaurantWithVoteTo> getWithVote(@RequestParam("date") LocalDate date) {
        return restaurantService.getAllWithVotesOnDate(date);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        return restaurantService.getAll();
    }

    @PostMapping(path = "/menu/{menuId}/voting")
    public void makeVote(@PathVariable("menuId") int menuId) {
        if (LocalTime.now().compareTo(VOTE_ACCEPTED_TIME_TO) < 0) {
            menuService.makeVote(menuId, AuthorizedUser.id(), LocalDate.now());
        } else {
            throw new VotingException("voting accepted only to 11:00:00");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMenu(@RequestBody MenuTo menuTo) {
        menuService.create(menuTo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMenu(@RequestBody MenuTo menuTo) {
        menuService.update(menuTo);
    }
}
