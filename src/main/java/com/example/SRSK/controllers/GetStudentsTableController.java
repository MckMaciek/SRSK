package com.example.SRSK.controllers;


import com.example.SRSK.model.Student;
import com.example.SRSK.model.User;
import com.example.SRSK.repositories.StudentRepo;
import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class GetStudentsTableController {

    @Autowired
    private StudentRepo repo;

    @Autowired
    public GetStudentsTableController(StudentRepo repo){
        this.repo = repo;
    }

    @RequestMapping(value = "/viewStudents", method = RequestMethod.GET)
    public ArrayList<Student> getStudents() {

        return repo.findAll();
    }

}
