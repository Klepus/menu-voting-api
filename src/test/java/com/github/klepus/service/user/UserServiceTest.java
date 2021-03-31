package com.github.klepus.service.user;

import com.github.klepus.model.User;
import com.github.klepus.service.AbstractServiceTest;
import com.github.klepus.service.user.UserService;
import com.github.klepus.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.klepus.TestUtil.assertMatch;
import static com.github.klepus.UserTestData.USER1;
import static com.github.klepus.UserTestData.USER1_ID;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testCreate() {
        User created = new User("Новый юзер", "test.mail@mail.ru", "pass123", false);
        User actualSaved = service.create(created);
        created.setId(actualSaved.getId());

        assertMatch(actualSaved, created);
    }

    @Test
    public void testUpdate() {
        User updated = new User(USER1);
        updated.setPassword("new-password");
        service.update(updated);

        assertMatch(service.get(USER1_ID), updated);
    }

    @Test
    public void testGet() {
        assertMatch(service.get(USER1_ID), USER1);
    }

    @Test
    public void testGetNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(-1);
    }

    @Test
    public void testGetByEmail() {
        assertMatch(service.getByEmail(USER1.getEmail()), USER1);
    }
}