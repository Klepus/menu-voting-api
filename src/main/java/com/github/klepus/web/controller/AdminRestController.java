package com.github.klepus.web.controller;

import com.github.klepus.model.Restaurant;
import com.github.klepus.service.menu.MenuService;
import com.github.klepus.service.restaurant.RestaurantService;
import com.github.klepus.to.menu.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.github.klepus.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(path = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {
    public static final String REST_URL = "/rest/admin/restaurant";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMenu(@RequestBody MenuTo menuTo) {
        menuService.create(menuTo);
    }

    @PutMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMenu(@RequestBody MenuTo menuTo) {
        menuService.update(menuTo);
    }
}
