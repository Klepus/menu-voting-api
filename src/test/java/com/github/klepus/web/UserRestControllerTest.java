package com.github.klepus.web;

import com.github.klepus.TestUtil;
import com.github.klepus.model.User;
import com.github.klepus.service.user.UserService;
import com.github.klepus.web.controller.UserRestController;
import com.github.klepus.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.klepus.UserTestData.getForCreation;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestController.REST_URL + "/";

    @Autowired
    private UserService service;

    @Test
    public void testCreate() throws Exception {
        User expected = getForCreation();
        mockMvc.perform(post(REST_URL + "registration")
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isOk())
                .andDo(print());

        User actual = service.getByEmail(expected.getEmail());
        actual.setId(expected.getId());
        TestUtil.assertMatch(actual, expected, "password");
    }
}
