package com.example.conference.services;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import com.example.conference.repositories.LectureRepository;
import com.example.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private LectureRepository lectureRepository;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(LectureRepository lectureRepository, UserRepository userRepository) {
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
    }
    @Override
    public User addUserToLecture(Long lectureId, String login, String email) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie znaleziono wykładu o takim id"));
        if(lecture.getUserSet().size() >= 5){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Limit miejsc wykorzystany, brak możliwości zapisu na wykład");
        }
        User user = getUserIfExist(login,email);
        if(user != null){
            user.getLectureSet().add(lecture);
        }
        else{
            Set<Lecture> lectures = new HashSet<>();
            lectures.add(lecture);
            user = new User(null,login,email,lectures);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public Set<Lecture> getAllUserLecture(String login) {
        return lectureRepository.getLecture(login);
    }

    private User getUserIfExist(String login, String email){
        User user = userRepository.findUserByLogin(login);
        if(user != null && !(user.getEmail().equals(email))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Podany login jest już zajęty");
        }
        return user;
    }

}
