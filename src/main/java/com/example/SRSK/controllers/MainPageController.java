package com.example.SRSK.controllers;

import com.example.SRSK.model.User;
import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainPageController {

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public ModelAndView indexS(Principal principal, User user, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int startingCharExcludingEmail = 7;
        String[] authParsed = auth.toString().split(",");
        String email = authParsed[1].substring(startingCharExcludingEmail).replace("'","");

        model.addAttribute("username",principal.getName());
        model.addAttribute("email",email);

        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/main", method= RequestMethod.GET)
    public ModelAndView index(Principal principal, User user, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final int startingCharExcludingEmail = 7;
        String[] authParsed = auth.toString().split(",");
        String email = authParsed[1].substring(startingCharExcludingEmail).replace("'","");


        model.addAttribute("username",principal.getName());
        model.addAttribute("email",email);

        User testowy = userRepo.findByEmail(email);
        System.out.println(testowy);

        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }


    @RequestMapping(value = "/about", method= RequestMethod.GET)
    public ModelAndView about() {

        ModelAndView mav = new ModelAndView("about.html");
        return mav;
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public ModelAndView getCode(){

        ModelAndView mav = new ModelAndView("getCode.html");
        mav.addObject("image_id", 1);
        return mav;
    }


}
