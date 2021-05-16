package com.example.kursach_proba.repository;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.DonationItem;
import com.example.kursach_proba.persistence.entity.DonationList;
import com.example.kursach_proba.persistence.repository.DonationItemRepository;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DonationItemRepositoryTest extends KursachProbaApplicationTests {


    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    private DonationItemRepository donationItemRepository;

    @Test
    public void findAllByDonationList() {
        DonationList donationList = new DonationList();
        donationList.setDonationListId(1);
        Iterable<DonationItem> donationLists = donationItemRepository.findAllByDonationList(donationList);
        assertNotNull(donationLists);
        assertEquals(2, IterableUtil.sizeOf(donationLists));
    }

    @Test
    public void findAllByNeedyOptionIdTest() {
        Iterable<DonationItem> donationItemsByNeedyOptionId = donationItemRepository.findAllByNeedyOptionId(3);
        assertNotNull(donationItemsByNeedyOptionId);
        assertEquals(1, IterableUtil.sizeOf(donationItemsByNeedyOptionId));
    }
}
