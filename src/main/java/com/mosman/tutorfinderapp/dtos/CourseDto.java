package com.mosman.tutorfinderapp.dtos;

import com.mosman.tutorfinderapp.models.Topic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CourseDto {

    private String courseName;
    private String courseDesc;
    private String coursePic;
    private MultipartFile file;
    private String topics;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }
}
