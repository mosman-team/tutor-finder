package com.mosman.tutorfinderapp.controlles;


import com.mosman.tutorfinderapp.models.ERole;
import com.mosman.tutorfinderapp.models.Role;
import com.mosman.tutorfinderapp.models.User;
import com.mosman.tutorfinderapp.payload.request.LoginRequest;
import com.mosman.tutorfinderapp.payload.request.SignupRequest;
import com.mosman.tutorfinderapp.payload.response.JwtResponse;
import com.mosman.tutorfinderapp.payload.response.MessageResponse;
import com.mosman.tutorfinderapp.repos.RoleRepo;
import com.mosman.tutorfinderapp.repos.UserRepo;
import com.mosman.tutorfinderapp.security.jwt.JwtUtils;
import com.mosman.tutorfinderapp.security.services.MailSender;
import com.mosman.tutorfinderapp.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private MailSender mailSender;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<User> optionalUser = userRepo.findByUsername(loginRequest.getUsername());

        if (optionalUser.isPresent()){
            User userFromDb = optionalUser.get();

            if (userFromDb.getActivationCode() != null){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Account is not activated!"));
            }
        }else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username or Password Incorrect!"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            addRole(roles, ERole.ROLE_STUDENT);
        }else {
            strRoles.forEach(role -> {
                if ("teacher".equals(role)) {
                    addRole(roles, ERole.ROLE_TEACHER);
                }else if ("student".equals(role)){
                    addRole(roles, ERole.ROLE_STUDENT);
                }
            });
        }


        user.setRoles(roles);
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to TutorFinder. Please, visit this link to activate your account: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );


        mailSender.send(user.getEmail(), "Activation Code", message);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void addRole(Set<Role> roles, ERole role){
        Role teacherRole = roleRepo.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(teacherRole);
    }

}

























