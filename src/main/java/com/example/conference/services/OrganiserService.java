package com.example.conference.services;

import com.example.conference.entities.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface OrganiserService {

    String getConferencePlan();
    Set<User> getAllUser();

}