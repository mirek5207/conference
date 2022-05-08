package com.example.conference.services;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import com.example.conference.repositories.LectureRepository;
import com.example.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

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
        Lecture lecture = getLecture(lectureId);
        User user = getUserIfExist(login,email);
        Set<Lecture> lectureSet = user.getLectureSet();
        lectureSet.add(lecture);
        if(lecture.getUserSet().size() >= 5){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Limit miejsc wykorzystany, brak możliwości zapisu na wykład");
        }
        else{
            user = User.builder()
                    .login(login)
                    .email(email)
                    .lectureSet(lectureSet)
                    .build();
            userRepository.save(user);
        }

        return user;
    }
    private Lecture getLecture(Long lectureId){
        Lecture lecture = null;
        try{
            lecture = lectureRepository.findById(lectureId).get();
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie znaleziono wykładu");
        }
        return lecture;
    }
    private User getUserIfExist(String login,String email){
        User user = userRepository.findUserByLogin(login);
        if(user != null && (user.getEmail() != email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Podany login jest już zajęty");
        }
        return user;
    }

}
