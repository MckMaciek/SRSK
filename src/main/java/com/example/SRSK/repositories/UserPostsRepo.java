package com.example.SRSK.repositories;


import com.example.SRSK.model.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserPostsRepo extends JpaRepository<UserPosts,Long> {

    ArrayList<UserPosts> findAll();


}
