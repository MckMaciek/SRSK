package com.example.SRSK;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

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

        //User user3 = new User("AAAA","BBBB" );

        //repo.save(user3);
        //User existing = entityManager.find(User.class,saved.getId());

    }



}
