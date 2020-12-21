package com.example.SRSK.controllers;


import com.example.SRSK.model.Student;
import com.example.SRSK.model.User;
import com.example.SRSK.repositories.StudentRepo;
import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value="/addStudentFromSite", method = RequestMethod.POST)
    public ModelAndView postStudent(@ModelAttribute Student student, Model model){

        Student latestStudent = repo.findTopByOrderByIdDesc();
        student.setId(latestStudent.getId() + 1);
        repo.save(student);

      return new ModelAndView("redirect:/viewStudents");
    }

    @RequestMapping(value = "/viewStudents", method = RequestMethod.GET)
    public ModelAndView getStudents(Model model) {

        model.addAttribute("newStudent", new Student());
        model.addAttribute("students", repo.findAll());

        return new ModelAndView("studentsView.html");
    }

}
