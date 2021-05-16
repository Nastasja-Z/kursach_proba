package com.example.kursach_proba.service;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserServiceTest extends KursachProbaApplicationTests {

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setUsername("test");
        User result = userService.save(user);
        assertNotNull(result);
        assertEquals("test", result.getUsername());
    }

    @Test
    public void findByUsernameTest() {
        User user = userService.findByUsername("testuser");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }
}
