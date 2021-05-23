package com.example.kursach_proba.view;


import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.service.NeedyService;
import com.example.kursach_proba.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NeediesFullListController {

    @Autowired
    private NeedyService needyService;

    @Autowired
    private OptionService optionService;

    @GetMapping("/needies")
    public String catalogueOpen(Model model) {
        model.addAttribute("needies", needyService.getAllNeedies());
        model.addAttribute("options", optionService.getAllOptions());
        return "needies";
    }

    @GetMapping("/getNeedyDetails/{needyId}")
    public String getNeedyDetails(Model model, @PathVariable("needyId") Integer needyId) {
        model.addAttribute("needy", needyService.findNeedyById(needyId));
        return "needyDetails";
    }

    @RequestMapping(value = "/getAllNeedies", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Needy> getAllNeedies() {
        return needyService.getAllNeedies();
    }

    @RequestMapping(value = "/filterByOption/{optionId}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Needy> filterByOption(@PathVariable("optionId") Integer optionId) {
        return needyService.findByOptionId(optionId);
    }

}

