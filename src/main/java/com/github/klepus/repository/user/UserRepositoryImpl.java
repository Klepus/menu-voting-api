package com.github.klepus.repository.user;

import com.github.klepus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }
}
