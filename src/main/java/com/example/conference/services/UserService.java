package com.example.conference.services;

import com.example.conference.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User addUserToLecture(Long lectureId, String login, String email);
}
