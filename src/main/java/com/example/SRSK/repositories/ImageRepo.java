package com.example.SRSK.repositories;

import com.example.SRSK.model.ImageDB;
import com.example.SRSK.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageDB,Long> {
    ImageDB findByCode(String code);
}
