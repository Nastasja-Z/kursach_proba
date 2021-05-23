package com.example.kursach_proba.service;


import com.example.kursach_proba.cache.config.BasketCacheConfig;
import com.example.kursach_proba.cache.data.BasketData;
import com.example.kursach_proba.persistence.entity.Needy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasketCacheConfig basketCacheConfig;

    @Autowired
    private  NeedyService needyService;

    public void addToBasket(String sessionId, Needy option) {
        try {
            Needy needy=needyService.findNeedyById(option.getNeedyId());
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.addOption(needy);
        } catch (ExecutionException e) {
            logger.error("ERROR during adding option", +option.getOptionId(), e);
        }

    }

    public void deleteOption(String sessionId, Needy option) {
        try {
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.removeOption(option);

        } catch (ExecutionException e) {
            logger.error("ERROR during deleting option " + option.getOptionId(), e);
        }
    }

    public void changeOptionAmount(String sessionId, Needy option, Integer newAmount) {
        try {
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.changeOptionAmount(option, newAmount);
        } catch (ExecutionException e) {
            logger.error("ERROR during deleting option " + option.getOptionId(), e);
        }
    }

    public Map<Needy, Integer> readBasketItems(String sessionId) {
        try {
            return basketCacheConfig.getCache().get(sessionId).readBasketItems();
        } catch (Exception e) {
            logger.error("ERROR during reading basket data: ", e);
            return null;
        }
    }

    public double countTotalPrice(Map<Needy, Integer> basketItems) {
        double result = 0d;
        for (Map.Entry<Needy, Integer> entry : basketItems.entrySet()) {
            result += (entry.getKey().getOption().getPrice() * entry.getValue());
        }
        return result;
    }

    public Needy findOptionByIdFromCache(String sessionId, Integer optionId) {
        Map<Needy, Integer> basketItems = readBasketItems(sessionId);
        List<Needy> filteredOptions = basketItems.keySet().stream()
                .filter(option -> option.getOptionId().equals(optionId))
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(filteredOptions) ? null : filteredOptions.get(0);
    }

    public void invalidateCache() {
        basketCacheConfig.getCache().invalidateAll();
    }

    public BasketData getBasketData(String sessionId) {
        return basketCacheConfig.getCache().getUnchecked(sessionId);
    }

}
