package com.example.kursach_proba.service;

import com.example.kursach_proba.cache.data.BasketData;
import com.example.kursach_proba.persistence.entity.*;
import com.example.kursach_proba.persistence.repository.DonationListRepository;
import com.example.kursach_proba.translator.BasketDataTranslator;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DonationListService {

    @Autowired
    private DonationListRepository donationListRepository;

    @Autowired
    DonationItemService donationItemService;

    @Autowired
    private BasketDataTranslator basketDataTranslator;

    @Transactional
    public void saveListFromBasket(BasketData basketData, User user) {
        DonationList donationList = basketDataTranslator.userBasketDataToOrder(basketData, user);
        DonationList savedList = donationListRepository.save(donationList);
        //??????????????
        donationItemService.saveDonationItems(savedList.getDonationItems());

    }

    private Map<Needy, Long> groupByOption(List<DonationItem> donationItems) {
        return donationItems.stream().collect(Collectors.groupingBy(DonationItem::getOption, Collectors.counting()));
    }

    public Iterable<DonationList> findListsByUser(User user) {
        Iterable<DonationList> result = donationListRepository.findAllByUserOrderByCreatedDate(user);
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            DonationList donationList = (DonationList) iterator.next();
            List<DonationItem> donationItemList = Lists.newArrayList(donationItemService.getDonationItems(donationList));
            donationList.setGroupedOptions(groupByOption(donationItemList));

        }
        return result;


    }


}
