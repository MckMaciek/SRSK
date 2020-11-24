package com.example.SRSK.API;

import com.example.SRSK.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentApi {

    static private List<Student> studentList;

    public StudentApi() {
        this.studentList = new ArrayList<Student>();
        studentList.add(new Student(1L,"Maciej","Musiał","18","9999"));
    }

    @GetMapping("/all")
    public static List<Student> getAll(){
        return studentList;
    }
    @GetMapping
    public static Student getByID(@RequestParam long index){
        Optional<Student> first = studentList.stream().
                filter(element -> element.getId() == index).findFirst();
        return first.get();
    }
    @PostMapping
    public static boolean addStudent(@RequestBody Student student){
        return studentList.add(student);
    }
    @PutMapping
    public static boolean updateStudent(@RequestBody Student student){
        return studentList.add(student);
    }
    @DeleteMapping
    public boolean deleteStudent(@RequestParam long index){
        return studentList.removeIf(element -> element.getId() == index);
    }


}
