package com.example.SRSK.controllers;


import com.example.SRSK.model.User;
import com.example.SRSK.model.UserPosts;
import com.example.SRSK.repositories.UserPostsRepo;
import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserPostsController {

    @Autowired
    UserPostsRepo userPostsRepo;

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/userPostsView", method = RequestMethod.GET)
    public ModelAndView newsBoardPage(Model model){

        model.addAttribute("addNewPostModel", new UserPosts());
        model.addAttribute("addedPosts", userPostsRepo.findAll());

        ModelAndView mav = new ModelAndView("newsBoard.html");
        return mav;
    }

    @RequestMapping(value = "/userPostsAdd", method = RequestMethod.POST)
    public ModelAndView adminAddPost(@ModelAttribute UserPosts userPosts, Model model, Principal principal){

        DateFormat dateformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        userPosts.setDate(dateformat.format(currentDate));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final int startingCharExcludingEmail = 7;
        String[] authParsed = auth.toString().split(",");
        String email = authParsed[1].substring(startingCharExcludingEmail).replace("'","");

        User getCurrentUser = userRepo.findByEmail(email);

        UserPosts userPostsToAdd = new UserPosts(getCurrentUser,userPosts.getPostDescription(),userPosts.getHeader(), userPosts.getDate());


        userPostsRepo.save(userPostsToAdd);

        ModelAndView mav = new ModelAndView("redirect:" + "/userPostsView");
        return mav;
    }




}
