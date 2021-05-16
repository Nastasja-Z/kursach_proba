package com.example.kursach_proba.repository;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.User;
import com.example.kursach_proba.persistence.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserRepositoryTest extends KursachProbaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        User user = userRepository.findByUsername("testuser");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }
}
