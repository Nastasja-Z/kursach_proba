package com.example.kursach_proba.persistence.repository;

import com.example.kursach_proba.persistence.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//МОЖЕТ ОН НЕ НУЖЕН??
public interface OptionRepository extends JpaRepository<Option, Integer> {

    //Iterable<Option> findAllByOrderByNameOfOption();

//    //могут быть вопросы!!!!
//    @Query("FROM Option o WHERE o.needy.needyId = :needyId ORDER BY o.nameOfOption")
//    Iterable<Option> findAllByNeedyId(@Param("needyId") Integer needyId);

//    @Query("FROM Option o ORDER BY o.nameOfOption, o.price")
//    Iterable<Option> findAllOrderByTree();

}
