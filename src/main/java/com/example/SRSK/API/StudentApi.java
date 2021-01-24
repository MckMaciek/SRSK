package com.example.SRSK.API;

import com.example.SRSK.model.Student;
import com.example.SRSK.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentApi {

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/all")
    public List<Student> getAll(){
        return studentRepo.findAll();
    }
    @GetMapping
    public Student getByID(@RequestParam String index){
        Optional<Student> first = studentRepo.findAll().stream().
                filter(element -> element.getIndex() == index).findFirst();
        return first.get();
    }
    @PostMapping
    public  void addStudent(@RequestBody Student student){
        studentRepo.save(student);
    }
    @PutMapping
    public void updateStudent(@RequestBody Student student){
        studentRepo.save(student);
    }
    @DeleteMapping
    public boolean deleteStudent(@RequestParam String index){
        return studentRepo.findAll().removeIf(element -> element.getIndex() == index);
    }


}
