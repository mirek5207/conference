package com.example.conference.repositories;

import com.example.conference.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByLogin(String login);
    Set<User> findAll();
}
