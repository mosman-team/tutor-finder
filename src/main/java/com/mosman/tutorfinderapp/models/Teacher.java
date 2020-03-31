package com.mosman.tutorfinderapp.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
public class Teacher extends User{

    private int experience;


    public Teacher() {

    }

    public Teacher(String username, String email, String password) {
        super(username, email, password);
    }
    public Teacher(String username, String email, String password, int experience) {
        super(username, email, password);
        this.experience = experience;
    }


    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}