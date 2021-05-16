package com.example.kursach_proba.service;

import com.example.kursach_proba.persistence.entity.DonationItem;
import com.example.kursach_proba.persistence.entity.DonationList;
import com.example.kursach_proba.persistence.repository.DonationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationItemService {

    @Autowired
    private DonationItemRepository donationItemRepository;

    public void saveDonationItems(Iterable<DonationItem> donationItems) {
        for (DonationItem item : donationItems) {
            donationItemRepository.save(item);
        }
    }

    public Iterable<DonationItem> getDonationItems(DonationList donationList) {
        return donationItemRepository.findAllByDonationList(donationList);
    }

    //возвращает опшены по имени ниди
    public Iterable<DonationItem> findDonationItemsByNeedy(Integer optionId) {
        return donationItemRepository.findAllByNeedyOptionId(optionId);
    }

}
