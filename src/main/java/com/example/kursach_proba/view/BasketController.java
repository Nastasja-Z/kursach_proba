package com.example.kursach_proba.view;

import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.entity.User;
import com.example.kursach_proba.service.BasketService;
import com.example.kursach_proba.service.DonationListService;
import com.example.kursach_proba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private DonationListService donationListService;

    @Autowired
    private UserService userService;

    @GetMapping("/basket/open")
    public String openBasket(Model model) {
        Map<Needy, Integer> basketItems = basketService.readBasketItems(getSessionId());
        model.addAttribute("selectedNeediesMap", basketItems);
        model.addAttribute("totalPrice", basketService.countTotalPrice(basketItems));
        return "basket";
    }

    private String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    @RequestMapping(value = "/basket/addToBasket", method = RequestMethod.POST)
    public String addToBasket(@ModelAttribute("needy") Needy needy) {
        basketService.addToBasket(getSessionId(), needy);
        return "redirect:/needies";
    }

    @RequestMapping(value = "/basket/changeNeediesAmount", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeNeediesAmount(@RequestParam("needyId") Integer needyId, @RequestParam("neediesAmount") Integer neediesAmount) {
        Needy needy = basketService.findOptionByIdFromCache(getSessionId(), needyId);
        basketService.changeOptionAmount(getSessionId(), needy, neediesAmount);
    }

    @RequestMapping(value = "/basket/deleteFromBasket", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFromBasket(@RequestParam("needyId") Integer needyId) {
        Needy needy = basketService.findOptionByIdFromCache(getSessionId(), needyId);
        //deleteNeedy?
        basketService.deleteOption(getSessionId(), needy);
    }


    @GetMapping("/basket/basketAmount")
    public @ResponseBody Integer getBasketItemsAmount() {
        return basketService.readBasketItems(getSessionId()).size();
    }

    @GetMapping("/basket/totalPrice")
    public @ResponseBody Double getTotalPrice() {
        return basketService.countTotalPrice(basketService.readBasketItems(getSessionId()));
    }

    @RequestMapping(value = "saveOrder", method = RequestMethod.GET)
    public String saveOrder() {
        //autorize?
        User user = userService.findByUsername("testuser");
        donationListService.saveListFromBasket(basketService.getBasketData(getSessionId()), user);
        basketService.invalidateCache();
        return "redirect:/orders/open";
    }

}
