package com.example.SRSK.controllers;


import com.example.SRSK.model.Student;
import com.example.SRSK.model.User;
import com.example.SRSK.repositories.StudentRepo;
import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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


        int currentPage = 1;

        model.addAttribute("newStudent", new Student());
        Pageable pageable = PageRequest.of(0,30);  // PAGING

        Page<Student> page = repo.findAll(pageable);
        List<Student> listStudents = page.getContent();

        int totalPages = page.getTotalPages();

        System.out.println(page.getTotalElements());


        model.addAttribute("students", listStudents);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalAmountOfPages", page.getTotalPages());
        model.addAttribute("totalAmountOfStudents", page.getTotalElements());

        //return new ModelAndView("studentsView.html");
        return listByPage(model, 1);
    }


    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView listByPage(Model model, @PathVariable("pageNumber") int pageNumber) {

        model.addAttribute("newStudent", new Student());
        Pageable pageable = PageRequest.of(pageNumber - 1,30);  // PAGING

        Page<Student> page = repo.findAll(pageable);
        List<Student> listStudents = page.getContent();

        int totalPages = page.getTotalPages();

        System.out.println(page.getTotalElements());


        model.addAttribute("students", listStudents);

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalAmountOfPages", page.getTotalPages());
        model.addAttribute("totalAmountOfStudents", page.getTotalElements());

        return new ModelAndView("studentsView.html");
        //studentsView.html
    }






    }
