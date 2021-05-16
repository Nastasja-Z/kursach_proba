package com.example.kursach_proba.service;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.Option;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OptionServiceRepository extends KursachProbaApplicationTests {

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Autowired
    OptionService optionService;

    @Test
    public void getRootOptionsTest() {
        Iterable<Option> allOptions = optionService.getAllOptions();
        assertNotNull(allOptions);
        assertEquals(7, IterableUtil.sizeOf(allOptions));

    }

    @Test
    public void findOptionByIdTest() {
        Option option = optionService.findOptionById(1);
        assertNotNull(option);
        assertEquals(Integer.valueOf(1), option.getOptionId());
        assertEquals("Social package: food", option.getNameOfOption());

    }

    @Test
    public void saveOptionTest() {
        Option option = new Option();
        option.setNameOfOption("new option");
        option.setPrice(800);
        optionService.saveOption(option);
        Iterable<Option> allOptions = optionService.getAllOptions();
        assertEquals(8, IterableUtil.sizeOf(allOptions));
        List<Option> optionList = Lists.newArrayList(allOptions);
        assertEquals("new option", optionList.get(7).getNameOfOption());
    }

    @Test
    public void deleteOptionTest() {
        Option option = new Option();
        option.setNameOfOption("test");
        Option resultOption = optionService.saveOption(option);
        optionService.deleteOption(resultOption.getOptionId());
        Iterable<Option> allOptions = optionService.getAllOptions();
        assertNotNull(allOptions);
        assertEquals(7, IterableUtil.sizeOf(allOptions));

    }


}
