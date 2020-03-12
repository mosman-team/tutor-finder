package com.mosman.tutorfinderapp.controlles;

import com.mosman.tutorfinderapp.models.User;
import com.mosman.tutorfinderapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String main(Model model){
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code){

        User user = userRepo.findByActivationCode(code);

        user.setActivationCode(null);

        userRepo.save(user);

        System.out.println("---------------ACTIVATED-------------");

        return "redirect:/login";
    }


}