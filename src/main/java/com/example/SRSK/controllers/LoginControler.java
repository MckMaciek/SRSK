package com.example.SRSK.controllers;

import com.example.SRSK.SecurityConfig;
import com.example.SRSK.User;
import com.example.SRSK.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginControler {


    @Autowired
    private UserRepo repo;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView addStudent(@ModelAttribute("SpringWeb") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            System.out.println("ERROR"); // do zmiany
        }

        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("role", "ROLE_ADMIN");

        System.out.println("LOGOWANIE");
        System.out.println(model.getAttribute("email"));
        System.out.println(model.getAttribute("password"));
        System.out.println(model.getAttribute("role"));


        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());

        List<User> licenses = repo.findAll();
        System.out.println("[DIAGNOZA] /getUser");
        for (User lic : licenses) {
            if (repo.existsByEmail(user.getEmail())) {
                    System.out.println("Mail w db");
                if (encoder.matches(user.getPassword(), lic.getPassword())) {
                    System.out.println("Haslo to");
                    System.out.println("/getUser Zalogowano");
                    ModelAndView mav = new ModelAndView("redirect:" + "/main");
                    return mav;
                }
            }
        }

        ModelAndView mav = new ModelAndView("redirect:" + "/login");
        return mav;

        /*
        if (repo.exists(Example.of(new_user))){


            System.out.println("Zalogowano");
            ModelAndView mav = new ModelAndView("index.html");
            return mav;
        }
        else {
            System.out.println("Nie istnieje");
            ModelAndView mav = new ModelAndView("login.html");
            return mav;
        }*/
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login.html");
        return mav;
    }
}
