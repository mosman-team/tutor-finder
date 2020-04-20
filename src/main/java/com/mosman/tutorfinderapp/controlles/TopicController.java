package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Topic;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.payload.request.SwapTopics;
import com.mosman.tutorfinderapp.repos.TopicRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {

    @Autowired
    private TopicRepo topicRepo;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/courses/{courseId}/topics")
    @JsonView(Views.IdName.class)
    public Topic addTopicToCourse(@PathVariable("courseId") Course course, @RequestBody Topic topic){
        topic.setCourse(course);
        return topicRepo.save(topic);
    }
    @GetMapping("/courses/{courseId}/topics")
    @JsonView(Views.IdName.class)
    public List<Topic> getCourseTopics(@PathVariable("courseId") Long courseId){
        return topicRepo.getTopicsByCourseId(courseId);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/courses/{courseId}/topics/{topicId}")
    @JsonView(Views.IdName.class)
    public Topic getCourseTopics(@PathVariable("topicId") Topic topicFromDb, @RequestBody Topic topic){
        topicFromDb.setTitle(topic.getTitle());
        topicFromDb.setHours(topic.getHours());
        return topicRepo.save(topicFromDb);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/courses/{courseId}/topics/{topicId}")
    public void deleteCourseTopic(@PathVariable("topicId") Topic topic){
        topicRepo.delete(topic);
    }

    @PutMapping("/courses/{courseId}/topics")
    public void swapTopics(@RequestBody SwapTopics swapTopics){
        Topic firstTopic = topicRepo.findById((long) swapTopics.getFirstTopicId()).get();
        Topic secondTopic = topicRepo.findById((long) swapTopics.getSecondTopicId()).get();


        String firstTitle = firstTopic.getTitle();
        int firstHours = firstTopic.getHours();

        firstTopic.setTitle(secondTopic.getTitle());
        firstTopic.setHours(secondTopic.getHours());

        secondTopic.setTitle(firstTitle);
        secondTopic.setHours(firstHours);


        topicRepo.save(firstTopic);
        topicRepo.save(secondTopic);
    }

}











