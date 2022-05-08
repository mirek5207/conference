package com.example.conference.services;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import com.example.conference.repositories.LectureRepository;
import com.example.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Lecture lecture = lectureRepository.findById(lectureId).get();
        User user = User.builder()
                .login(login)
                .email(email)
                .lecture(lecture)
                .build();
        userRepository.save(user);
        return user;
    }

}
