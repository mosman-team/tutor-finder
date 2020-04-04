package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.exception.ResourceNotFoundException;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Teacher;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.TeacherRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/teachers") ///{teacherId}/course
@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;


    @GetMapping("/{teacherId}/courses")
    @JsonView(Views.IdName.class)
    public Set<Course> getCourse(@PathVariable("teacherId") Teacher teacher){
        return teacher.getCourses();
    }

    @PostMapping("/{teacherId}/courses")
    @JsonView(Views.IdName.class)
    public Course createCourse(
            @PathVariable("teacherId") Long teacherId,
            @RequestBody Course course){
        return teacherRepo.findById(teacherId).map(teacher -> {
            course.setTeacher(teacher);
            return courseRepo.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("Teacher " + teacherId + " not found"));
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Course courseFromDb, @RequestBody Course course){
        BeanUtils.copyProperties(course, courseFromDb, "id");
        return courseRepo.save(courseFromDb);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long id){
        courseRepo.deleteById(id);
    }

}
