package com.github.klepus.repository.user;

import com.github.klepus.model.User;
import com.github.klepus.repository.AbstractRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.klepus.TestUtil.assertMatch;
import static com.github.klepus.UserTestData.USER1;
import static com.github.klepus.UserTestData.USER1_ID;

public class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testCreate() {
        User created = new User("Новый юзер", "test.mail@mail.ru", "pass123", false);
        User actualSaved = repository.save(created);
        created.setId(actualSaved.getId());

        assertMatch(actualSaved, created);
    }

    @Test
    public void testUpdate() {
        User updated = new User(USER1);
        updated.setPassword("new-password");
        User actualUpdated = repository.save(updated);

        assertMatch(actualUpdated, updated);
    }

    @Test
    public void testGet() {
        assertMatch(repository.get(USER1_ID), USER1);
    }

    @Test
    public void testGetByEmail() {
        assertMatch(repository.getByEmail(USER1.getEmail()), USER1);
    }
}