package com.example.kursach_proba.persistence.repository;

import com.example.kursach_proba.persistence.entity.DonationItem;
import com.example.kursach_proba.persistence.entity.DonationList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationItemRepository extends JpaRepository<DonationItem, Integer> {

    Iterable<DonationItem> findAllByDonationList(DonationList donationList);

    Iterable<DonationItem> findAllByNeedyOptionId(Integer optionId);

}

