package com.mosman.tutorfinderapp.models;

public final class Views {

    public interface Id{ }

    public interface IdName extends Id{ }

    public interface FullInfo extends IdName{}

    public interface CourseWithTeacher extends FullInfo{}

}
