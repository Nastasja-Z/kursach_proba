package com.example.kursach_proba.translator;

import com.example.kursach_proba.cache.data.BasketData;
import com.example.kursach_proba.persistence.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class BasketDataTranslator {

    public DonationList userBasketDataToOrder(BasketData basketData, User user) {
        DonationList donationList = new DonationList();
        donationList.setUser(user);
        Iterator iterator = basketData.readBasketItems().entrySet().iterator();
        List<DonationItem> orderItems = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Needy, Integer> basketItem = (Map.Entry<Needy, Integer>) iterator.next();
            for (int i = 0; i < basketItem.getValue(); i++) {
                DonationItem item = new DonationItem();
                item.setOption(basketItem.getKey());
                item.setDonationList(donationList);
                orderItems.add(item);
            }
        }
        donationList.setDonationItems(orderItems);
        return donationList;
    }

}

