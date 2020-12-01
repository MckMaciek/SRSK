package com.example.SRSK.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity
public class ImageDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;
    @Lob
    @Column(name = "image" , columnDefinition="BLOB")
    private Blob image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public ImageDB(String code, Blob image) {
        this.code = code;
        this.image = image;
    }

    public ImageDB() {
    }

}
