package com.example.kursach_proba.service;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.entity.Option;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class NeedyServiceTest extends KursachProbaApplicationTests {
    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    NeedyService needyService;

    @Test
    public void getAllNeediesTest() {
        Iterable<Needy> allNeedies = needyService.getAllNeedies();
        assertNotNull(allNeedies);
        assertEquals(6, IterableUtil.sizeOf(allNeedies));
    }

    @Test
    public void findByOptionIdTest() {
        Iterable<Needy> neediesByOption = needyService.findByOptionId(2);
        assertNotNull(neediesByOption);
        assertEquals(1, IterableUtil.sizeOf(neediesByOption));
        List<Needy> needyList = Lists.newArrayList(neediesByOption);
        assertEquals("Yellow Moon", needyList.get(0).getNameNeedy());
    }

    @Test
    public void findNeedyByIdTest() {
        Needy needy = needyService.findNeedyById(1);
        assertNotNull(needy);
        assertEquals("Donald Duck", needy.getNameNeedy());
        assertEquals("/storage/donald.gif", needy.getPhotoPath());
        assertEquals("status 1", needy.getStatus());
    }

    private Needy saveNeedy() {
        Needy needy = new Needy();
        Option option = new Option();
        //??
        option.setOptionId(4);
        needy.setNameNeedy("test needy");
        needy.setStatus("test status");
        needy.setPhotoPath("/storage/donald.gif");
        needy.setOption(option);
        return needyService.saveNeedy(needy);
    }

    @Test
    public void saveNewNeedyTest() {
        saveNeedy();
        Iterable<Needy> allNeedies = needyService.getAllNeedies();
        assertNotNull(allNeedies);
        assertTrue(IterableUtil.sizeOf(allNeedies) > 6);
        Needy resultNeedy = needyService.findNeedyById(7);
        assertEquals("test needy", resultNeedy.getNameNeedy());
        assertEquals("test status", resultNeedy.getStatus());
        assertEquals("/storage/donald.gif", resultNeedy.getPhotoPath());
    }

    @Test
    public void updateNeedyTest() {
        Needy needy = saveNeedy();
        Option option = new Option();
        option.setOptionId(5);
        needy.setNameNeedy("Upd test name");
        needy.setStatus("Upd test status");
        needy.setPhotoPath("/storage/test.jpg");
        needy.setOption(option);
        needyService.saveNeedy(needy);
        Iterable<Needy> allNeedies = needyService.getAllNeedies();
        assertNotNull(allNeedies);
        assertEquals(7, IterableUtil.sizeOf(allNeedies));
        Needy resultNeedy = needyService.findNeedyById(needy.getNeedyId());
        assertEquals("Upd test name", resultNeedy.getNameNeedy());
        assertEquals("Upd test status", resultNeedy.getStatus());
        assertEquals("/storage/test.jpg", resultNeedy.getPhotoPath());
        assertEquals(Integer.valueOf(5), resultNeedy.getOption().getOptionId());
    }

    @Test
    public void deleteNeedyTest() {
        Needy needy = needyService.getAllNeedies().iterator().next();
        needyService.deleteNeedy(needy.getNeedyId());
        Iterable<Needy> allNeedies = needyService.getAllNeedies();
        assertNotNull(allNeedies);
        //or 6 ??
        assertEquals(5, IterableUtil.sizeOf(allNeedies));
    }


}
