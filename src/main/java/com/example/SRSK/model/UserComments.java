package com.example.SRSK.model;


import javax.persistence.*;

@Entity
@Table(name="user_comments_tb")

public class UserComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserPosts userPosts;

    @Column(name = "post_desc")
    private String postDescription;
    @Column(name = "post_header")
    private String header;
    @Column(name = "date")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPosts getUser() {
        return userPosts;
    }

    public void setUser(UserPosts user) {
        this.userPosts = userPosts;
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

    public UserComments() {
    }

    public UserComments(UserPosts userPosts, String postDescription, String header, String date) {
        this.userPosts = userPosts;
        this.postDescription = postDescription;
        this.header = header;
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserComments{" +
                "postDescription='" + postDescription + '\'' +
                '}';
    }
}
