package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.dtos.CourseDto;
import com.mosman.tutorfinderapp.exception.ResourceNotFoundException;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Teacher;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.TeacherRepo;
import com.mosman.tutorfinderapp.services.file_upload.FilesStorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/teachers") ///{teacherId}/course
@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    FilesStorageService storageService;

    @GetMapping("/{teacherId}/courses")
    @JsonView(Views.IdName.class)
    public Set<Course> getCourse(@PathVariable("teacherId") Teacher teacher){
        return teacher.getCourses();
    }

    @PostMapping("/{teacherId}/courses")
    @JsonView(Views.IdName.class)
    public Course createCourse(
            @PathVariable("teacherId") Long teacherId,
            @ModelAttribute CourseDto courseDto
    ){
        return teacherRepo.findById(teacherId).map(teacher -> {
            Course newCourse = new Course();
            newCourse.setCourseName(courseDto.getCourseName());
            newCourse.setCourseDesc(courseDto.getCourseDesc());

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + courseDto.getFile().getOriginalFilename();

            newCourse.setTeacher(teacher);
            storageService.save(courseDto.getFile(), resultFileName);
            newCourse.setCoursePic(resultFileName);
            return courseRepo.save(newCourse);
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
