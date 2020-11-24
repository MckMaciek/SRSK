package com.example.SRSK.repositories;

import com.example.SRSK.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    User findByUsername(String username);
    User findByEmail(String email);

}
