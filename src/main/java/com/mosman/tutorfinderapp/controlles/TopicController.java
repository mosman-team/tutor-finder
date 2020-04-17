package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Topic;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.TopicRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers") ///{teacherId}/course
@PreAuthorize("hasRole('TEACHER')")
public class TopicController {

    @Autowired
    private TopicRepo topicRepo;

    @PostMapping("/{teacherId}/courses/{courseId}/topics")
    @JsonView(Views.IdName.class)
    public Topic addTopicToCourse(@PathVariable("courseId") Course course, @RequestBody Topic topic){
        topic.setCourse(course);
        return topicRepo.save(topic);
    }
    @GetMapping("/{teacherId}/courses/{courseId}/topics")
    @JsonView(Views.IdName.class)
    public List<Topic> getCourseTopics(@PathVariable("courseId") Long courseId){
        return topicRepo.getTopicsByCourseId(courseId);
    }
    @PutMapping("/{teacherId}/courses/{courseId}/topics/{topicId}")
    @JsonView(Views.IdName.class)
    public Topic getCourseTopics(@PathVariable("topicId") Topic topicFromDb, @RequestBody Topic topic){
        topicFromDb.setTitle(topic.getTitle());
        topicFromDb.setHours(topic.getHours());
        return topicRepo.save(topicFromDb);
    }

    @DeleteMapping("/{teacherId}/courses/{courseId}/topics/{topicId}")
    @JsonView(Views.IdName.class)
    public void deleteCourseTopic(@PathVariable("topicId") Topic topic){
        topicRepo.delete(topic);
    }

}











