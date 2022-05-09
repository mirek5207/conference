package com.example.conference.controllers;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import com.example.conference.request.UserRequest;
import com.example.conference.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conference/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/reservation/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUserToLecture(@RequestParam Long lectureId, @RequestBody UserRequest userRequest){
        User user = userService.addUserToLecture(lectureId, userRequest.getLogin(), userRequest.getEmail());
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/lecture")
    public ResponseEntity<Set<Lecture>> getAllUserLecture(@RequestParam String login){
        Set<Lecture> lectureList = userService.getAllUserLecture(login);
        return ResponseEntity.ok(lectureList);
    }
}
