package com.example.SRSK.repositories;

import com.example.SRSK.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
ArrayList<Student> findAll();

}