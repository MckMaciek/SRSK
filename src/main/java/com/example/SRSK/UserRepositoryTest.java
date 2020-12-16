package com.example.SRSK;

import com.example.SRSK.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@EnableAutoConfiguration
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//@Rollback(false)
@Component

public class UserRepositoryTest {

    private UserRepo repo;

    @Autowired
    public UserRepositoryTest(UserRepo repo){
        this.repo = repo;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void testCreateUser(){



    }



}
