package com.example.SRSK.model;

public class Student {

    private Long id;
    private String fname;
    private String lname;
    private String age;
    private String index;

    public Student() {
    }

    public Student(Long id, String fname, String lname, String age, String index) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.index = index;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
