package com.example.kursach_proba.persistence.repository;

import com.example.kursach_proba.persistence.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//МОЖЕТ ОН НЕ НУЖЕН??
public interface OptionRepository extends JpaRepository<Option, Integer> {


}
