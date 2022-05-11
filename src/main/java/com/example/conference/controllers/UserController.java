package com.example.conference.controllers;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import com.example.conference.request.UpdateUserRequest;
import com.example.conference.request.UserRequest;
import com.example.conference.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Reservation to the lecture by user")
    @PostMapping(value = "/lecture/reservation/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUserToLecture(@RequestParam Long lectureId, @RequestBody UserRequest userRequest){
        User user = userService.addUserToLecture(lectureId, userRequest.getLogin(), userRequest.getEmail());
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get plan of conference", description = "Provides plan of conference")
    @GetMapping(value = "/plan")
    public String getPlanOfConference(){
        return userService.getConferencePlan();
    }

    @Operation(summary = "Return all reserved lectures after entering the user login")
    @GetMapping(value = "/lectures")
    public ResponseEntity<Set<Lecture>> getAllUserLecture(@RequestParam String login){
        Set<Lecture> lectureSet = userService.getAllUserLecture(login);
        return ResponseEntity.ok(lectureSet);
    }

    @Operation(summary = "Update email lecture after entering user login")
    @PatchMapping(value = "/email")
    public ResponseEntity<User> updateUserEmail(@RequestParam String login, @RequestBody UpdateUserRequest updateUserRequest){
        User user = userService.updateEmail(login,updateUserRequest.getEmail());
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Cancel reservation for lecture after entering lecture id, login and email")
    @DeleteMapping(value = "/lecture/reservation/")
    public ResponseEntity<?> deleteReservation(@RequestParam Long lectureId, @RequestBody UserRequest userRequest){
        userService.deleteReservation(lectureId,userRequest.getLogin(),userRequest.getEmail());
        return ResponseEntity.noContent().build();
    }
}
