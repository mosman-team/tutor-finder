package com.mosman.tutorfinderapp.controlles;


import com.fasterxml.jackson.annotation.JsonView;
import com.mosman.tutorfinderapp.dtos.UserDto;
import com.mosman.tutorfinderapp.models.User;
import com.mosman.tutorfinderapp.models.Views;
import com.mosman.tutorfinderapp.payload.response.MessageResponse;
import com.mosman.tutorfinderapp.payload.response.UserDtoResponse;
import com.mosman.tutorfinderapp.repos.UserRepo;
import com.mosman.tutorfinderapp.security.jwt.JwtUtils;
import com.mosman.tutorfinderapp.services.file_upload.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FilesStorageService storageService;

    @GetMapping("/{id}")
    @JsonView(Views.IdName.class)
    public User getUser(@PathVariable("id") User user){
        return user;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@ModelAttribute UserDto userDto, Principal principal) throws IOException {
        /*
        1) file is empty and user img null
        do nothing
        2) file is empty img exists
        do noting
        3) file come user img null
        save file and set user img name
        4) file come img exists
         delete old file save new File set img name
        */

        Optional<User> optionalUser = userRepo.findByUsername(principal.getName());

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            if (userDto.getFile() != null){
                if (user.getImg() != null){
                    storageService.delete(user.getImg());
                }
                String resultFilename = TeacherController.getResultFileName(userDto.getFile().getOriginalFilename());
                storageService.save(userDto.getFile(), resultFilename);
                user.setImg(resultFilename);
                userRepo.save(user);
            }
            UserDtoResponse res = new UserDtoResponse(optionalUser.get().getImg());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Something in wrong"));
    }
}





