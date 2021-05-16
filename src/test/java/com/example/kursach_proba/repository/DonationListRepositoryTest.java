package com.example.kursach_proba.repository;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.DonationList;
import com.example.kursach_proba.persistence.entity.User;
import com.example.kursach_proba.persistence.repository.DonationListRepository;
import com.example.kursach_proba.service.UserService;
import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DonationListRepositoryTest extends KursachProbaApplicationTests {


    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    DonationListRepository donationListRepository;

    @Autowired
    private UserService userService;

    @Test
    public void findAllByUserTest() {
        User user = userService.findByUsername("testuser");
        Iterable<DonationList> donationLists = donationListRepository.findAllByUserOrderByCreatedDate(user);
        assertNotNull(donationLists);
        assertEquals(2, IterableUtil.sizeOf(donationLists));
    }
}
