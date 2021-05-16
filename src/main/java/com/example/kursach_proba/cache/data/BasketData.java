package com.example.kursach_proba.cache.data;

import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.entity.Option;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class BasketData {

    private final Integer INITIAL_AMOUNT = 1;

    private Map<Needy, Integer> basketItems = new HashMap<>();

    public ImmutableMap<Needy, Integer> readBasketItems() {
        return ImmutableMap.copyOf(basketItems);
    }

    public void changeOptionAmount(Needy option, Integer newAmount) {
        if (basketItems.containsKey(option)) {
            Integer optionAmount = basketItems.get(option);
            basketItems.replace(option, optionAmount, newAmount);
        }
    }

    public void addOption(Needy option) {
        if (basketItems.containsKey(option)) {
            changeOptionAmount(option, basketItems.get(option) + 1);
            return;
        }
        basketItems.put(option, INITIAL_AMOUNT);

    }

    public void removeOption(Needy option) {
        basketItems.remove(option);
    }

}
