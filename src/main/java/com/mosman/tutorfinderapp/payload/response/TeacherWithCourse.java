package com.mosman.tutorfinderapp.payload.response;

import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Teacher;

import java.util.List;
import java.util.Set;

public class TeacherWithCourse {

    private Teacher teacher;
    private Course course;

    public TeacherWithCourse(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
