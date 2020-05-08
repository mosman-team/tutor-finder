package com.mosman.tutorfinderapp.repos;

import com.mosman.tutorfinderapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findAllByCourseId(Long id);
}
