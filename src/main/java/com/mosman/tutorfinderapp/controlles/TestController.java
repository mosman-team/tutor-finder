package com.mosman.tutorfinderapp.controlles;

import com.mosman.tutorfinderapp.dtos.AppInfo;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.StudentRepo;
import com.mosman.tutorfinderapp.repos.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudentRepo studentRepo;


    @GetMapping("/appInfo")
    public AppInfo info(){
        AppInfo info = new AppInfo(teacherRepo.count(), courseRepo.count(), studentRepo.count());
        return info;
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public String studentAccess(){
        return "student Content";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String adminAccess() {
        return "Teacher Board.";
    }
}






















