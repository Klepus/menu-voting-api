package com.github.klepus.repository.user;


import com.github.klepus.model.User;

public interface UserRepository {
    User save(User user);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);
}
