package com.mosman.tutorfinderapp.models;

import javax.persistence.*;


@Entity
public class Student extends User{

    private int grade;

    public Student() {
    }

    public Student(String username, String email, String password) {
        super(username, email, password);
    }
    public Student(String username, String email, String password, int grade) {
        super(username, email, password);
        this.grade = grade;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
