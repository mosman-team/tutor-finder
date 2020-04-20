package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Teacher;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.payload.response.TeacherWithCourse;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.CourseWithTeacher.class)
    public Course getTeacherWithCourse(@PathVariable("id") Course course){
        return course;
    }

}













