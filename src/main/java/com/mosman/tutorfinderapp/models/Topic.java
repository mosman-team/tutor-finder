package com.mosman.tutorfinderapp.models;

import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Long;

    private String title;
    private int hours;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Topic() {
    }

    public int getLong() {
        return Long;
    }

    public void setLong(int aLong) {
        Long = aLong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
