package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.dtos.EventType;
import com.mosman.tutorfinderapp.dtos.ObjectType;
import com.mosman.tutorfinderapp.exception.NotAuthorizedException;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Message;
import com.mosman.tutorfinderapp.models.User;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.MessageRepo;
import com.mosman.tutorfinderapp.repos.UserRepo;
import com.mosman.tutorfinderapp.utils.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/course")
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    private final BiConsumer<EventType, Message> wsSender;

    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullInfo.class);
    }

    @GetMapping("/{courseId}/messages")
    @JsonView(Views.IdName.class)
    public List<Message> getAllMessages(@PathVariable("courseId") Long id){
        return messageRepo.findAllByCourseId(id);
    }

    @GetMapping("/messages/{messageId}")
    @JsonView(Views.FullInfo.class)
    public Message getOne(@PathVariable("messageId") Message message) {
        return message;
    }

    @PostMapping("/{courseId}/messages")
    @JsonView(Views.FullInfo.class)
    public Message create(@PathVariable("courseId") Course course,
                          @RequestBody Message message,
                          Principal principal) {
        message.setCreationDate(LocalDateTime.now());


        if (principal == null){
            throw new NotAuthorizedException("User is not present");
        }

        Optional<User> optionalUser = userRepo.findByUsername(principal.getName());

        if (optionalUser.isPresent()){
            message.setUser(optionalUser.get());
        }else {
            throw new NotAuthorizedException("User is not present");
        }

        message.setCourse(course);

        Message updatedMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, updatedMessage);

        return updatedMessage;
    }

    @PutMapping("/messages/{messageId}")
    @JsonView(Views.FullInfo.class)
    public Message update(
            @PathVariable("messageId") Message messageFromDb,
            @RequestBody Message message
    ) {
//        BeanUtils.copyProperties(message, messageFromDb, "id");

        messageFromDb.setText(message.getText());

        Message updatedMessage = messageRepo.save(messageFromDb);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("/messages/{messageId}")
    public void delete(@PathVariable("messageId") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

}









