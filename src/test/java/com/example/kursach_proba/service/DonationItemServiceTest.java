package com.example.kursach_proba.service;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.DonationItem;
import com.example.kursach_proba.persistence.entity.DonationList;
import com.example.kursach_proba.persistence.entity.Needy;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DonationItemServiceTest extends KursachProbaApplicationTests {
    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    private DonationItemService donationItemService;

    @Test
    public void saveDonationItemTest() {
        DonationList donationList = new DonationList();
        donationList.setDonationListId(1);
        Needy needyFirst = new Needy();
        needyFirst.setNeedyId(1);
        Needy needySecond = new Needy();
        needySecond.setNeedyId(1);
        DonationItem donationItemFirst = new DonationItem();
        donationItemFirst.setOption(needyFirst);
        donationItemFirst.setDonationList(donationList);
        DonationItem donationItemSecond = new DonationItem();
        donationItemSecond.setOption(needySecond);
        donationItemSecond.setDonationList(donationList);
        donationItemService.saveDonationItems(Arrays.asList(donationItemFirst, donationItemSecond));
        Iterable<DonationItem> donationItems = donationItemService.getDonationItems(donationList);
        assertNotNull(donationItems);
        assertEquals(4, IterableUtil.sizeOf(donationItems));

    }

    @Test
    public void getDonationItemsTest() {
        DonationList donationList = new DonationList();
        donationList.setDonationListId(1);
        Iterable<DonationItem> donationItems = donationItemService.getDonationItems(donationList);
        assertNotNull(donationItems);
        // or 3
        assertEquals(4, IterableUtil.sizeOf(donationItems));
    }


}
