package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.dtos.CourseAdditionalInfo;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Teacher;
import com.mosman.tutorfinderapp.models.Topic;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.payload.response.TeacherWithCourse;
import com.mosman.tutorfinderapp.repos.CourseRepo;
import com.mosman.tutorfinderapp.repos.TeacherRepo;
import com.mosman.tutorfinderapp.utils.KMP_String_Matching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping
    @JsonView(Views.FullInfo.class)
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.CourseWithTeacher.class)
    public Course getTeacherWithCourse(@PathVariable("id") Course course){
        return course;
    }


    @PostMapping("/filter")
    @JsonView(Views.IdName.class)
    public List<Course> filterCourses(@RequestBody CourseAdditionalInfo cinfo){

        List<Course> allCourses = courseRepo.findAll();

        List<Course> filteredCourses = new ArrayList<>();


        for (Course course: allCourses) {

            if (course.getCity() != null){
                if (course.getCity().equalsIgnoreCase(cinfo.getCity())
                        && course.getPrice() <= cinfo.getPrice() && course.getLanguage().equalsIgnoreCase(cinfo.getLanguage())){
                    filteredCourses.add(course);
                }
            }

        }
        return filteredCourses;
    }

    @PostMapping("/search")
    @JsonView(Views.IdName.class)
    public List<Course> searchCourses(@RequestBody String search){

        List<Course> allCourses = courseRepo.findAll();

        List<Course> filteredCourses = new ArrayList<>();

        KMP_String_Matching searchAlg = new KMP_String_Matching();

        search = search.replace('+', ' ');
        search = search.substring(0, search.length() - 1);


        for (Course c: allCourses) {

            String courseDataString = c.getCourseName();

            Set<Topic> topics = c.getTopics();

            for (Topic t: topics) {
                courseDataString += " " + t.getTitle();
            }

            if (searchAlg.KMPSearch(search.toLowerCase(), courseDataString.toLowerCase())){
                filteredCourses.add(c);
            }
        }
        return filteredCourses;
    }

}













