package com.mosman.tutorfinderapp.repos;


import com.mosman.tutorfinderapp.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepo extends JpaRepository<Topic, Long> {

    public List<Topic> getTopicsByCourseId(Long id);
    public Topic deleteTopicById(Long id);
}
