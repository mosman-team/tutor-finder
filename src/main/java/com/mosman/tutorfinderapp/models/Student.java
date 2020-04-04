package com.mosman.tutorfinderapp.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Student extends User{

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> enrolledCourses;



    public Student() {
    }


    public Student(String username, String email, String password) {
        super(username, email, password);
    }

}
