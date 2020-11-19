package com.example.SRSK.controllers;

import com.example.SRSK.User;
import com.example.SRSK.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public ModelAndView addStudent(@ModelAttribute("SpringWeb") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            System.out.println("ERROR"); // do zmiany
        }

        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());

        System.out.println("LOGOWANIE");
        System.out.println(model.getAttribute("email"));
        System.out.println(model.getAttribute("password"));

        User new_user = new User(model.getAttribute("email").toString(),
                model.getAttribute("password").toString());


        List<User> licenses=repo.findAll();
        for(Object lic : licenses){
            System.out.println(lic);
        }


        if (repo.exists(Example.of(new_user))){
            System.out.println("Zalogowano");
            ModelAndView mav = new ModelAndView("index.html");
            return mav;
        }
        else {
            System.out.println("Nie istnieje");
            ModelAndView mav = new ModelAndView("login.html");
            return mav;
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login.html");
        return mav;
    }
}
