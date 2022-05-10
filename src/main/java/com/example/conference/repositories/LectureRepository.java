package com.example.conference.repositories;

import com.example.conference.entities.Lecture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Set;

public interface LectureRepository extends CrudRepository<Lecture,Long> {

    @Query(value = "SELECT LECTURE.LECTURE_ID,LECTURE.NAME,LECTURE.TIME_FRAME,LECTURE.COURSE_ID FROM LECTURE\n" +
            "INNER JOIN USER_LECTURE ON USER_LECTURE.LECTURE_ID = LECTURE.LECTURE_ID\n" +
            "INNER JOIN USER ON USER.USER_ID = USER_LECTURE.USER_ID\n" +
            "WHERE USER.LOGIN = :login"
            ,nativeQuery = true)
    Set<Lecture> getLecture(String login);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM USER_LECTURE WHERE USER_LECTURE.USER_ID = :userId AND USER_LECTURE.LECTURE_ID = :lectureId",nativeQuery = true)
    void deleteReservedLecture(Long userId, Long lectureId);
    
    @Query(value = "SELECT USER_LECTURE.LECTURE_ID, LECTURE.NAME, CONCAT((COUNT(USER_LECTURE.USER_ID))*100/5 ,'% obecności') FROM USER_LECTURE\n" +
            "INNER JOIN LECTURE ON LECTURE.LECTURE_ID = USER_LECTURE.LECTURE_ID \n" +
            "GROUP BY USER_LECTURE.LECTURE_ID"
            ,nativeQuery = true)
    Set<String> getPercentageOfUsersParticipatingInLectures();


}
