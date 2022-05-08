package com.example.conference.repositories;

import com.example.conference.entities.Lecture;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<Lecture,Long> {
}
