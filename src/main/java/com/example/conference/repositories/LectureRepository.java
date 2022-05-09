package com.example.conference.repositories;

import com.example.conference.entities.Lecture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LectureRepository extends CrudRepository<Lecture,Long> {
    @Query(value = "SELECT LECTURE.LECTURE_ID,LECTURE.NAME,LECTURE.TIME_FRAME,LECTURE.COURSE_ID FROM LECTURE\n" +
            "INNER JOIN USER_LECTURE ON USER_LECTURE.LECTURE_ID = LECTURE.LECTURE_ID\n" +
            "INNER JOIN USER ON USER.USER_ID = USER_LECTURE.USER_ID\n" +
            "WHERE USER.LOGIN = :login"
            ,nativeQuery = true)
    Set<Lecture> getLecture(String login);


}