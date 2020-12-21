package com.example.SRSK.repositories;

import com.example.SRSK.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    User findByUsername(String username);
    User findByEmail(String email);
    ArrayList<User> findAll();

}
