package com.example.kursach_proba.view;

import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.entity.User;
import com.example.kursach_proba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/registration/{donation_user}", method = RequestMethod.POST)
    public String addToUser(@ModelAttribute("donation_user") User user) {
        userService.save(user);
        return "redirect:/needies";
    }


//    @GetMapping("/getCurrentUser/{user}")
//    public @ResponseBody String getCurrentUser(@PathVariable("user") User user) {
//        //chto-to ne to        save(new User)
//        if(userService.findByUsername(user.getUsername())==null) {
//            User user2=userService.save(user);
//        } else {
//            User user1= userService.findByUsername(user.getUsername());
//        }
//        return "users";
//
//
//
//    }


}
