package com.example.conference.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "time_frame", nullable = false)
    private String timeFrame;

    @ManyToMany(mappedBy = "lectureSet")
    @JsonIgnore
    private Set<User> userSet = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private Course course;

}
