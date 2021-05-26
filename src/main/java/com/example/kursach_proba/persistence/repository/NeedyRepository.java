package com.example.kursach_proba.persistence.repository;

import com.example.kursach_proba.persistence.entity.Needy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NeedyRepository extends JpaRepository<Needy, Integer> {


    Iterable<Needy> findAllByOrderByNameNeedy();

    @Query("FROM Needy n WHERE n.option.optionId = :optionId ORDER BY n.nameNeedy")
    Iterable<Needy> findAllByOptionOptionId(@Param("optionId") Integer optionId);


}
