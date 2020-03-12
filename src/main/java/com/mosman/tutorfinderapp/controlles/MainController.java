package com.mosman.tutorfinderapp.controlles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String main(Model model){
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        return "redirect:/login";
    }

}