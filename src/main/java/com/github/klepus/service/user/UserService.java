package com.github.klepus.service.user;


import com.github.klepus.model.User;
import com.github.klepus.util.exception.NotFoundException;

public interface UserService {

    User create(User user);

    void update(User user);

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;
}
