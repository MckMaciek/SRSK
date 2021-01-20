package com.example.SRSK.repositories;

import com.example.SRSK.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student,Long> {


    ArrayList<Student> findAll();


    Student findTopByOrderByIdDesc();



}