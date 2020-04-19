package com.mosman.tutorfinderapp.repos;

import com.mosman.tutorfinderapp.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    public Optional<Teacher> findByUsername(String username);

}
