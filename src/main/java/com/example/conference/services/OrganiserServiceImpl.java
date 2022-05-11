package com.example.conference.services;

import com.example.conference.entities.User;
import com.example.conference.repositories.CourseRepository;
import com.example.conference.repositories.LectureRepository;
import com.example.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class OrganiserServiceImpl implements OrganiserService {

    private UserRepository userRepository;
    private LectureRepository lectureRepository;
    private CourseRepository courseRepository;

    @Autowired
    public OrganiserServiceImpl(UserRepository userRepository, LectureRepository lectureRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Set<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Set<String> getLecturesReport() {
        return lectureRepository.getPercentageOfUsersParticipatingInLectures();
    }

    @Override
    public Set<String> getCoursesReport() {
        return courseRepository.getPercentageOfUsersParticipatingInEachCourse();
    }

}
