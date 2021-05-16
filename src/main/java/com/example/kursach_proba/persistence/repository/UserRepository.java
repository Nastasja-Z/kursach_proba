package com.example.kursach_proba.persistence.repository;

import com.example.kursach_proba.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
