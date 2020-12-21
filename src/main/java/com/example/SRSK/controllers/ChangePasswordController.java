package com.example.SRSK.controllers;

import com.example.SRSK.model.User;
import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserRepo repo;


    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView getPasswordChangeScreen() {

    return new ModelAndView("changeCredentials.html");
    }


    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView addStudent(@ModelAttribute User user, BindingResult result, ModelMap model, Principal principal) {

        //--------------------------------------GETTING EMAIL----------------------------------------------------
        final int startingCharExcludingEmail = 7;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String[] authParsed = auth.toString().split(",");
        String email = authParsed[1].substring(startingCharExcludingEmail).replace("'","");
        //--------------------------------------/GETTING EMAIL----------------------------------------------------

        PasswordEncoder encoder =  new BCryptPasswordEncoder();
        model.addAttribute("password", user.getPassword());
        model.addAttribute("username", user.getUsername());
        String hashedPassword = encoder.encode(user.getPassword());

        User existingUser = repo.findByEmail(email);
        existingUser.setPassword(hashedPassword);
        existingUser.setUsername(user.getUsername());
        repo.save(existingUser);

        return new ModelAndView("redirect:" + "/logout");
    }




}
