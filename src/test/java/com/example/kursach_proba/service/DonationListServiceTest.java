package com.example.kursach_proba.service;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.DonationList;
import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.entity.User;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DonationListServiceTest extends KursachProbaApplicationTests {

    @Autowired
    private NeedyService needyService;

    @Autowired
    private DonationListService donationListService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    private final String SESSION_ID = "sessionId";


    @Test
    public void saveDonationListFromBasketTest() {
        User user = userService.findByUsername("testuser");
        donationListService.saveListFromBasket(basketService.getBasketData(SESSION_ID), user);
        Iterable<DonationList> resultDonationList = donationListService.findListsByUser(user);
        assertNotNull(resultDonationList);
        assertTrue(IterableUtil.sizeOf(resultDonationList) > 2);
        List<DonationList> resultList = Lists.newArrayList(resultDonationList);
        DonationList result = resultList.get(2);
        assertEquals(user.getUsername(), result.getUser().getUsername());
        assertEquals(4, result.getGroupedOptions().size());
    }

    @Test
    public void findDonationListWithItemsByUserTest() {
        User user = userService.findByUsername("testuser");
        Iterable<DonationList> resultDonationList = donationListService.findListsByUser(user);
        assertNotNull(resultDonationList);
        assertTrue(IterableUtil.sizeOf(resultDonationList) == 2);
    }

    @Override
    protected void setUp() throws Exception {
        List<Needy> needies = Lists.newArrayList(needyService.getAllNeedies());
        basketService.addToBasket(SESSION_ID, needies.get(0));
        basketService.addToBasket(SESSION_ID, needies.get(0));
        basketService.addToBasket(SESSION_ID, needies.get(0));
        basketService.addToBasket(SESSION_ID, needies.get(1));
        basketService.addToBasket(SESSION_ID, needies.get(2));
        basketService.addToBasket(SESSION_ID, needies.get(2));
        basketService.addToBasket(SESSION_ID, needies.get(4));
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
