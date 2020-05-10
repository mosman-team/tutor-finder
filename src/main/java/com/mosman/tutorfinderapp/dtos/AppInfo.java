package com.mosman.tutorfinderapp.dtos;

public class AppInfo {

    private Long nOfTeachers;
    private Long nOfCourses;
    private Long nOfStudents;

    public AppInfo(Long nOfTeachers, Long nOfCourses, Long nOfStudents) {
        this.nOfTeachers = nOfTeachers;
        this.nOfCourses = nOfCourses;
        this.nOfStudents = nOfStudents;
    }

    public Long getnOfTeachers() {
        return nOfTeachers;
    }

    public void setnOfTeachers(Long nOfTeachers) {
        this.nOfTeachers = nOfTeachers;
    }

    public Long getnOfCourses() {
        return nOfCourses;
    }

    public void setnOfCourses(Long nOfCourses) {
        this.nOfCourses = nOfCourses;
    }

    public Long getnOfStudents() {
        return nOfStudents;
    }

    public void setnOfStudents(Long nOfStudents) {
        this.nOfStudents = nOfStudents;
    }
}
