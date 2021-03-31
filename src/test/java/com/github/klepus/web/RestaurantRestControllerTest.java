package com.github.klepus.web;

import com.github.klepus.service.restaurant.RestaurantService;
import com.github.klepus.web.controller.RestaurantRestController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.klepus.RestaurantTestData.*;
import static com.github.klepus.TestUtil.contentJson;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1_TO));
    }

    @Test
    public void testGetOneWithMeals() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID1 + "/menu").param("date", DATE_STR))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getOneWithMenuOnDate(RESTAURANT_ID1, DATE)));
    }

    @Test
    public void testGetOneWithVote() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID1 + "/vote").param("date", DATE_STR))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getOneWithVoteOnDate(RESTAURANT_ID1, DATE)));
    }

    @Test
    public void testGetAllWithMeals() throws Exception {
        mockMvc.perform(get(REST_URL + "menu").param("date", DATE_STR))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getAllWithMenusOnDate(DATE)));
    }

    @Test
    public void testGetAllWithVote() throws Exception {
        mockMvc.perform(get(REST_URL + "vote").param("date", DATE_STR))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getAllWithVotesOnDate(DATE)));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(contentJson(service.getAll()));
    }
}