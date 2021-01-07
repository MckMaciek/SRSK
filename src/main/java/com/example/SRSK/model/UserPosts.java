package com.example.SRSK.model;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="user_posts_tb")

public class UserPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private UserComments userComments;

    @Column(name = "post_desc")
    private String postDescription;
    @Column(name = "post_header")
    private String header;
    @Column(name = "date")
    private String date;


    public UserPosts(User user, String postDescription, String header, String date) {
        this.user = user;
        this.postDescription = postDescription;
        this.header = header;
        this.date = date;
    }

    public UserPosts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserComments getUserComments() {
        return userComments;
    }

    public void setUserComments(UserComments userComments) {
        this.userComments = userComments;
    }

    public String printOP() {
        return "[EMAIL] " +
                user.getEmail() +
                " [USERNAME] " + user.getUsername() + '\n' +
                " [ROLE] " + user.getRole() + " ";
    }



}
