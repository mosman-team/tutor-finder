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
import com.mosman.tutorfinderapp.services.file_upload.FilesStorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

            String resultFileName = "test2.png";
            if (courseDto.getFile() != null){
                String uuidFile = UUID.randomUUID().toString();
                resultFileName = uuidFile + "." + courseDto.getFile().getOriginalFilename();
                storageService.save(courseDto.getFile(), resultFileName);
            }
            newCourse.setTeacher(teacher);
            newCourse.setCoursePic(resultFileName);

            return courseRepo.save(newCourse);
        }).orElseThrow(() -> new ResourceNotFoundException("Teacher " + teacherId + " not found"));
    }
    private List<Topic> getCourseTopics(String topics){

        List<Topic> listTopics = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            listTopics = Arrays.asList(mapper.readValue(topics, Topic[].class));
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }

        return listTopics;
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
