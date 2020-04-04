package com.mosman.tutorfinderapp.repos;

import com.mosman.tutorfinderapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {


}
