package com.mosman.tutorfinderapp.repos;

import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Course> findAllById(Long id);
}
