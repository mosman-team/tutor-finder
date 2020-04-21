package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Student;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

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

    @PutMapping("/{id}")
    @JsonView(Views.Id.class)
    public Set<Course> enrollStudent(@PathVariable("id") Course course, Principal principal){
        Student student = studentRepo.findByUsername(principal.getName()).get();
        if (student.getEnrolledCourses().contains(course)){
            student.removeCourse(course);
        }else {
            student.addCourse(course);
        }
        studentRepo.save(student);
        return student.getEnrolledCourses();
    }

    @GetMapping("/enrolledCourses")
    @JsonView(Views.Id.class)
    public Set<Course> enrollStudent(Principal principal){
        Student student = studentRepo.findByUsername(principal.getName()).get();
        return student.getEnrolledCourses();
    }
    @GetMapping("/student")
    @JsonView(Views.IdName.class)
    public Set<Course> getTeacherWithCourse(Principal principal){
        Student student = studentRepo.findByUsername(principal.getName()).get();
        return student.getEnrolledCourses();
    }


}













