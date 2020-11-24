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

        //User user3 = new User("AAAA","BBBB","nie","ROLE_ADMIN" );
        //repo.save(user3);
        //repo.save(user3);
        //User existing = entityManager.find(User.class,saved.getId());

    }



}
