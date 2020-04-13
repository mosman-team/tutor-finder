package com.mosman.tutorfinderapp.dtos;

import org.springframework.web.multipart.MultipartFile;

public class CourseDto {

    private String courseName;
    private String courseDesc;
    private MultipartFile file;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
