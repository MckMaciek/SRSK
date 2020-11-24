package com.example.SRSK.controllers;

import com.example.SRSK.model.Token;
import com.example.SRSK.model.User;
import com.example.SRSK.repositories.TokenRepo;
import com.example.SRSK.repositories.UserRepo;
import com.example.SRSK.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenRepo tokenrepo;

    @Autowired
    public RegistrationController(UserRepo repo, TokenRepo tokenrepo, MailService mailService) {
        this.repo = repo;
        this.tokenrepo = tokenrepo;
        this.mailService = mailService;
    }

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
        userToAdd.setEnabled(false);

        repo.save(userToAdd);
        sendToken(userToAdd);

        ModelAndView mav = new ModelAndView("registration_success.html");
        return mav;
    }

    @RequestMapping(value = "/token")
    public ModelAndView token(@RequestParam String value){
        Token newValue =   tokenrepo.findByValue(value);
        User user1 = newValue.getUser();
        user1.setEnabled(true);

        repo.save(user1);
        ModelAndView mav = new ModelAndView("registration_success.html");
        return mav;
    }


    private void sendToken(User userToAdd){
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(userToAdd);
        tokenrepo.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(userToAdd.getEmail(), "SRSK - Complete registration process", url, false);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
