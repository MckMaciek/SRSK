package com.example.SRSK.controllers;

import com.example.SRSK.SecurityConfig;
import com.example.SRSK.User;
import com.example.SRSK.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepo repo;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView mav = new ModelAndView("register.html");
        return mav;
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addStudent(@ModelAttribute User user, BindingResult result, ModelMap model){
        if (result.hasErrors()) {
            System.out.println("ERROR"); // do zmiany
        }

        //List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //authorities.add(new SimpleGrantedAuthority("USER"));

        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("username", user.getUsername());

        if (repo.existsByEmail(user.getEmail())) {
            System.out.println("Email duplicate in DB");
            ModelAndView mav = new ModelAndView("register.html");
            return mav;
        }

        PasswordEncoder encoder =  new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());

        User userToAdd = new User(model.getAttribute("email").toString(),
                hashedPassword, user.getUsername(),"ROLE_USER");

        userToAdd.setUsername(user.getUsername());

        repo.save(userToAdd);

        ModelAndView mav = new ModelAndView("registration_success.html");
        return mav;
    }


}
