package com.example.SRSK.model;


import javax.persistence.*;

@Entity
@Table(name="students_tb")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String fname;
    @Column(name = "last_name")
    private String lname;
    @Column(name = "sub_group")
    private String sub_group;
    @Column(name = "group_id")
    Integer group_id;
    @Column(name = "index_number")
    private String index;

    public Student() {
    }

    public Student(Long id, String fname, String lname, String sub_group, Integer group_id, String index) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.sub_group = sub_group;
        this.group_id = group_id;
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

    public String getSub_group() {
        return sub_group;
    }

    public void setSub_group(String sub_group) {
        this.sub_group = sub_group;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", sub_group='" + sub_group + '\'' +
                ", group_id=" + group_id +
                ", index='" + index + '\'' +
                '}';
    }

}
