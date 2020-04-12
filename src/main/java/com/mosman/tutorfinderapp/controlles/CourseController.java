package com.mosman.tutorfinderapp.controlles;

import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping("/{id}")
    public Course getOne(@PathVariable("id") Course course){
        return course;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    // Student

    @GetMapping("/student/{id}")
    public Course getStudentCourses(){
        return null;
    }


}
*/












