package com.example.kursach_proba.view;

import com.example.kursach_proba.persistence.entity.User;
import com.example.kursach_proba.service.DonationListService;
import com.example.kursach_proba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonationListController {


    @Autowired
    private DonationListService donationListService;

    @Autowired
    private UserService userService;
//need some registration

    @GetMapping("/orders/open")
    public String openOrders(Model model) {
        //trouble??
        User user = userService.save(new User());
        model.addAttribute("userOrders", donationListService.findListsByUser(user));
        return "userOrders";
    }

}
