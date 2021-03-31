package com.github.klepus.service.user;

import com.github.klepus.model.User;
import com.github.klepus.repository.CrudUserRepository;
import com.github.klepus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.github.klepus.util.ValidationUtil.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CrudUserRepository repository;

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.findByEmail(email), "email=" + email);
    }
}
