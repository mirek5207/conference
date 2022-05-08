package com.example.conference.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_lecture",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    @JsonIgnore
    private Set<Lecture> lectureSet;

}
