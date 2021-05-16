package com.example.kursach_proba.persistence.repository;

import com.example.kursach_proba.persistence.entity.DonationList;
import com.example.kursach_proba.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DonationListRepository extends JpaRepository<DonationList, Integer> {

    Set<DonationList> findAllByUserOrderByCreatedDate(User user);

}
