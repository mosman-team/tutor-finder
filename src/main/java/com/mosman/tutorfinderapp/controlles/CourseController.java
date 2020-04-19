package com.mosman.tutorfinderapp.controlles;

import com.mosman.tutorfinderapp.dtos.CourseAdditionalInfo;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @PostMapping("/{courseId}")
    public CourseAdditionalInfo addInfoToCourse(
            @PathVariable("courseId") Course course, @RequestBody CourseAdditionalInfo cInfo){

        course.setCity(cInfo.getCity());
        course.setAddress(cInfo.getAddress());
        course.setPrice(cInfo.getPrice());
        course.setLanguage(cInfo.getLanguage());
        course.setKeyWords(cInfo.getKeyWords());


        cInfo.setId(course.getId());
        courseRepo.save(course);

        return cInfo;
    }

}












