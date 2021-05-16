package com.example.kursach_proba.service;

import com.example.kursach_proba.KursachProbaApplicationTests;
import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.entity.Option;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasketServiceTest extends KursachProbaApplicationTests {

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }

    private final String SESSION_ID = "sessionId";
    @Autowired
    private BasketService basketService;

    @Test
    public void addSameOptionToBasketTest() {
        Needy option = new Needy();
        option.setNameNeedy("test");
        option.setStatus("test");
        option.setOptionId(1);
        basketService.addToBasket(SESSION_ID, option);
        assertEquals(1, basketService.readBasketItems(SESSION_ID).size());
        assertEquals(Integer.valueOf(1), basketService.readBasketItems(SESSION_ID).get(option));
        basketService.addToBasket(SESSION_ID, option);
        assertEquals(Integer.valueOf(2), basketService.readBasketItems(SESSION_ID).get(option));
    }

    @Test
    public void addDifferentOptionToBasketTest() {
        Needy firstOption = new Needy();
        firstOption.setNeedyId(1);
        firstOption.setNameNeedy("firstOption");
        firstOption.setStatus("test");
        Needy secondOption = new Needy();
        secondOption.setNeedyId(2);
        secondOption.setNameNeedy("secondOption");
        secondOption.setStatus("test");
        basketService.addToBasket(SESSION_ID, firstOption);
        assertEquals(Integer.valueOf(1), basketService.readBasketItems(SESSION_ID).get(firstOption));
        basketService.addToBasket(SESSION_ID, secondOption);
        assertEquals(2, basketService.readBasketItems(SESSION_ID).size());
        assertEquals(Integer.valueOf(1), basketService.readBasketItems(SESSION_ID).get(secondOption));
    }

    @Test
    public void deleteOptionFromBasket() {
        Needy firstNeedy = new Needy();
        firstNeedy.setNeedyId(1);
        firstNeedy.setNameNeedy("firstNeedy");
        firstNeedy.setNameNeedy("firstOption");
        Needy secondNeedy = new Needy();
        secondNeedy.setNeedyId(2);
        secondNeedy.setNameNeedy("secondNeedy");
        secondNeedy.setNameNeedy("firstOption");
        basketService.addToBasket(SESSION_ID, firstNeedy);
        basketService.addToBasket(SESSION_ID, secondNeedy);
        assertEquals(2, basketService.readBasketItems(SESSION_ID).size());
        basketService.deleteOption(SESSION_ID, secondNeedy);
        assertEquals(1, basketService.readBasketItems(SESSION_ID).size());
    }

    @Test
    public void expirationBasketTest() throws Exception {
        Needy needy = new Needy();
        needy.setNameNeedy("test");
        basketService.addToBasket(SESSION_ID, needy);
        assertEquals(1, basketService.readBasketItems(SESSION_ID).size());
        Thread.sleep(3000);
        assertEquals(0, basketService.readBasketItems(SESSION_ID).size());
    }

    @Test
    public void findNeedyByIdFromCacheTest() {
        Needy firstNeedy = new Needy();
        firstNeedy.setNeedyId(1);
        firstNeedy.setNameNeedy("firstNeedy");
        firstNeedy.setStatus("test");
        Needy secondNeedy = new Needy();
        secondNeedy.setNeedyId(2);
        secondNeedy.setNameNeedy("secondNeedy");
        secondNeedy.setStatus("test");
        basketService.addToBasket(SESSION_ID, firstNeedy);
        basketService.addToBasket(SESSION_ID, secondNeedy);
        Needy resultNeedy = basketService.findOptionByIdFromCache(SESSION_ID, 1);
        assertNotNull(resultNeedy);
        assertEquals(Integer.valueOf(1), resultNeedy.getNeedyId());
        assertEquals("firstNeedy", resultNeedy.getNameNeedy());
    }

    //?????
    @Test
    public void countTotalPriceTest() {
        Needy firstNeedy = new Needy();
        firstNeedy.setNeedyId(1);
        firstNeedy.setOptionId(1);
        //might setPrice
        firstNeedy.getOption().getPrice();
        firstNeedy.setNameNeedy("firstNeedy");
        Needy secondNeedy = new Needy();
        secondNeedy.setNeedyId(2);
        secondNeedy.setNameNeedy("secondNeedy");
        secondNeedy.setOptionId(2);
        secondNeedy.getOption().getPrice();
        basketService.addToBasket(SESSION_ID, firstNeedy);
        basketService.addToBasket(SESSION_ID, secondNeedy);
        basketService.addToBasket(SESSION_ID, secondNeedy);
        Map<Needy, Integer> basketItems = basketService.readBasketItems(SESSION_ID);
        assertEquals(320, basketService.countTotalPrice(basketItems), 0);
    }

    @Test
    public void changeProductAmountTest() {
        Needy firstNeedy = new Needy();
        firstNeedy.setNeedyId(1);
        firstNeedy.setOptionId(1);
        //might setPrice
        firstNeedy.getOption().getPrice();
        firstNeedy.setNameNeedy("firstNeedy");
        basketService.addToBasket(SESSION_ID, firstNeedy);
        basketService.changeOptionAmount(SESSION_ID, firstNeedy, 4);
        Map<Needy, Integer> basketItems = basketService.readBasketItems(SESSION_ID);
        assertEquals(Integer.valueOf(4), basketItems.get(firstNeedy));
    }


}
