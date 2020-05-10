package com.mosman.tutorfinderapp.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.dtos.EventType;
import com.mosman.tutorfinderapp.dtos.MetaDto;
import com.mosman.tutorfinderapp.dtos.ObjectType;
import com.mosman.tutorfinderapp.exception.NotAuthorizedException;
import com.mosman.tutorfinderapp.models.Course;
import com.mosman.tutorfinderapp.models.Message;
import com.mosman.tutorfinderapp.models.User;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.repos.MessageRepo;
import com.mosman.tutorfinderapp.repos.UserRepo;
import com.mosman.tutorfinderapp.utils.WsSender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/course")
public class MessageController {

    private static String URL_PATTERN = "https?:\\/\\/?[\\w\\d\\._\\-%\\/\\?=&#]+";
    private static String IMAGE_PATTERN = "\\.(jpeg|jpg|gif|png)$";

    private static Pattern URL_REGEX = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
    private static Pattern IMG_REGEX = Pattern.compile(IMAGE_PATTERN, Pattern.CASE_INSENSITIVE);

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    private final BiConsumer<EventType, Message> wsSender;

    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullInfo.class);
    }

    @GetMapping("/{courseId}/messages")
    @JsonView(Views.FullInfo.class)
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
                          Principal principal) throws IOException {
        message.setCreationDate(LocalDateTime.now());

        try {
            fillMeta(message);
        }catch (Exception e){

        }

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
    ) throws IOException {
//        BeanUtils.copyProperties(message, messageFromDb, "id");

        messageFromDb.setText(message.getText());

        try {
            fillMeta(message);
        }catch (Exception e){

        }

        Message updatedMessage = messageRepo.save(messageFromDb);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("/messages/{messageId}")
    public void delete(@PathVariable("messageId") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    private void fillMeta(Message message) throws IOException {
        String text = message.getText();
        Matcher matcher = URL_REGEX.matcher(text);

        if (matcher.find()) {
            String url = text.substring(matcher.start(), matcher.end());

            matcher = IMG_REGEX.matcher(url);

            message.setLink(url);

            if (matcher.find()) {
                message.setLinkCover(url);
            } else if (!url.contains("youtu")) {
                MetaDto meta = getMeta(url);

                message.setLinkCover(meta.getCover());
                message.setLinkTitle(meta.getTitle());
                message.setLinkDescription(meta.getDescription());
            }
        }
    }

    private MetaDto getMeta(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Elements title = doc.select("meta[name$=title],meta[property$=title]");
        Elements description = doc.select("meta[name$=description],meta[property$=description]");
        Elements cover = doc.select("meta[name$=image],meta[property$=image]");

        return new MetaDto(
                getContent(title.first()),
                getContent(description.first()),
                getContent(cover.first())
        );
    }

    private String getContent(Element element) {
        return element == null ? "" : element.attr("content");
    }

}









