package com.example.conference.services;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    String getConferencePlan();

    User addUserToLecture(Long lectureId, String login, String email);

    Set<Lecture> getAllUserLecture(String login);

    void deleteReservation(Long lectureId, String login, String email);

    User updateEmail(String login, String email);
}
