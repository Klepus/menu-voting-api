package com.github.klepus.web.controller;

import com.github.klepus.model.User;
import com.github.klepus.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserRestController.REST_URL)
public class UserRestController {
    public static final String REST_URL = "/rest/user";

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody User user) {
        service.create(user);
    }
}
