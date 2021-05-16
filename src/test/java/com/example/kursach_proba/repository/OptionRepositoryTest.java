package com.example.kursach_proba.repository;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.Option;
import com.example.kursach_proba.persistence.repository.OptionRepository;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class OptionRepositoryTest extends KursachProbaApplicationTests {

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    private OptionRepository optionRepository;

    // МОЖЕТ НЕ НУЖНО, ЕСЛИ РЕПОЗИТОРИЙ НЕ НУЖЕН
    @Test
    public void testFindAll() {
        Iterable<Option> options = optionRepository.findAll();
        assertNotNull(options);
        assertEquals(7, IterableUtil.sizeOf(options));
//        List<Option> optionList = Lists.newArrayList(options);
//        assertEquals("Linens", optionList.get(6).getNameOfOption());
//        assertEquals("Payment for gas", optionList.get(5).getNameOfOption());
//        assertEquals("Payment for light", optionList.get(4).getNameOfOption());
//        assertEquals("Payment for water", optionList.get(3).getNameOfOption());
//        assertEquals("Set of clothes", optionList.get(2).getNameOfOption());
//        assertEquals("Social package: food", optionList.get(1).getNameOfOption());
//        assertEquals("Superior social package: food", optionList.get(0).getNameOfOption());


    }


}
