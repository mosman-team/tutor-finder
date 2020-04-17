package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosman.tutorfinderapp.dtos.CourseDto;
import com.mosman.tutorfinderapp.exception.ResourceNotFoundException;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Teacher;
import com.mosman.tutorfinderapp.models.Topic;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.TeacherRepo;
import com.mosman.tutorfinderapp.repos.TopicRepo;
import com.mosman.tutorfinderapp.services.file_upload.FilesStorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
            newCourse.setTeacher(teacher);

            if (courseDto.getFile() != null){
                String resultFileName = getResultFileName(courseDto.getFile().getOriginalFilename());
                storageService.save(courseDto.getFile(), resultFileName);
                newCourse.setCoursePic(resultFileName);
            }
            return courseRepo.save(newCourse);

        }).orElseThrow(() -> new ResourceNotFoundException("Teacher " + teacherId + " not found"));
    }

    @PutMapping("/{teacherId}/courses/{courseId}")
    @JsonView(Views.IdName.class)
    public Course updateCourse(
            @ModelAttribute CourseDto courseDto,
            @PathVariable("courseId") Course courseFromDb
    ) throws IOException {

        courseFromDb.setCourseName(courseDto.getCourseName());
        courseFromDb.setCourseDesc(courseDto.getCourseDesc());
        System.out.println(courseDto);
        if (courseDto.getFile() == null && (courseDto.getCoursePic().equals("") || courseDto.getCoursePic() == null)){
            courseFromDb.setCoursePic(null);
        }
        if (courseDto.getFile() != null){
            if (courseFromDb.getCoursePic() != null){
                storageService.delete(courseFromDb.getCoursePic());
            }
            String resultFileName = getResultFileName(courseDto.getFile().getOriginalFilename());
            storageService.save(courseDto.getFile(), resultFileName);
            courseFromDb.setCoursePic(resultFileName);
        }
        System.out.println(courseFromDb);
        return courseRepo.save(courseFromDb);
    }
    private String getResultFileName(String originalFilename){
        String uuidFile = UUID.randomUUID().toString();
        return uuidFile + "." + originalFilename;
    }

}
