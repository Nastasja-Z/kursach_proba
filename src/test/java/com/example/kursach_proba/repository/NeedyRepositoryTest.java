package com.example.kursach_proba.repository;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.repository.NeedyRepository;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NeedyRepositoryTest extends KursachProbaApplicationTests {


    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    private NeedyRepository needyRepository;

    @Test
    public void findAllByOrderByNameNeedyTest() {
        Iterable<Needy> allNeedies = needyRepository.findAllByOrderByNameNeedy();
        assertNotNull(allNeedies);
        assertEquals(6, IterableUtil.sizeOf(allNeedies));
        List<Needy> needyList = Lists.newArrayList(allNeedies);
        assertEquals("Beast of Beauty", needyList.get(0).getNameNeedy());
        assertEquals("Donald Duck", needyList.get(1).getNameNeedy());
        assertEquals("Dopey", needyList.get(2).getNameNeedy());
        assertEquals("Grumpy", needyList.get(3).getNameNeedy());
        assertEquals("Guffi", needyList.get(4).getNameNeedy());
        assertEquals("Yellow Moon", needyList.get(5).getNameNeedy());
    }

    @Test
    public void findAllByOptionOptionIdTest() {
        Iterable<Needy> needyByOption = needyRepository.findAllByOptionOptionId(2);
        assertNotNull(needyByOption);
        assertEquals(1, IterableUtil.sizeOf(needyByOption));
        List<Needy> needyList = Lists.newArrayList(needyByOption);
        assertEquals("Yellow Moon", needyList.get(0).getNameNeedy());

    }
}
