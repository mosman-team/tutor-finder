package com.mosman.tutorfinderapp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Teacher extends User{

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    public Teacher() {

    }

    public Teacher(String username, String email, String password) {
        super(username, email, password);
    }
    public Teacher(String username, String email, String password, Set<Course> courses) {
        super(username, email, password);
        this.courses = courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}