package com.mosman.tutorfinderapp.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Teacher extends User{

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {

    }

    public Teacher(String username, String email, String password) {
        super(username, email, password);
    }
    public Teacher(String username, String email, String password, List<Course> courses) {
        super(username, email, password);
        this.courses = courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}