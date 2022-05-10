package com.example.conference.repositories;

import com.example.conference.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CourseRepository extends CrudRepository<Course,Long> {

    @Query(value = "SELECT COURSE.NAME,CONCAT(COUNT(USER_LECTURE.LECTURE_ID) * 100 / 15,  '% obecności w kursie') FROM COURSE\n" +
            "INNER JOIN LECTURE ON LECTURE.COURSE_ID = COURSE.ID \n" +
            "INNER JOIN USER_LECTURE ON USER_LECTURE.LECTURE_ID  = LECTURE.LECTURE_ID \n" +
            "GROUP BY COURSE.ID"
            ,nativeQuery = true)
    Set<String> getPercentageOfUsersParticipatingInEachCourse();
}
