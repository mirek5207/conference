package com.example.conference.controllers;

import com.example.conference.entities.User;
import com.example.conference.services.OrganiserService;
import com.example.conference.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class OrganiserController {

    private final OrganiserService organiserService;

    @GetMapping(value = "/plan")
    public String getPlanOfConference(){
        return organiserService.getConferencePlan();
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Set<User>> getAllUser(){
        Set<User> userSet = organiserService.getAllUser();
        return ResponseEntity.ok(userSet);
    }

    @GetMapping(value = "/lectures/report")
    public ResponseEntity<Set<String>> getLecturesReport(){
        Set<String> report = organiserService.getLecturesReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping(value = "/courses/report")
    public ResponseEntity<Set<String>> getCoursesReport(){
        Set<String> report = organiserService.getCoursesReport();
        return ResponseEntity.ok(report);
    }

}
