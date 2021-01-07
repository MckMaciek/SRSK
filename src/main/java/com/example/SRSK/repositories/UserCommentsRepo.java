package com.example.SRSK.repositories;


import com.example.SRSK.model.UserComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserCommentsRepo extends JpaRepository<UserComments,Long> {

    ArrayList<UserComments> findAll();
}
