package com.mosman.tutorfinderapp.models;


import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    @Column(name = "course_name")
    private String courseName;

    @JsonView(Views.IdName.class)
    @Column(name = "course_desc")
    private String courseDesc;

    @JsonView(Views.IdName.class)
    @Column(name = "course_pic")
    private String coursePic;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonView(Views.CourseWithTeacher.class)
    private Teacher teacher;

    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Set<Topic> topics;

    @JsonView(Views.FullInfo.class)
    private String city;
    @JsonView(Views.FullInfo.class)
    private String address;
    @JsonView(Views.FullInfo.class)
    private Long price;
    @JsonView(Views.FullInfo.class)
    private String language;

    @JsonView(Views.FullInfo.class)
    @ElementCollection
    private List<String> keyWords = new ArrayList<String>();

    public Course() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }
}
