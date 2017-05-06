package com.lab3.repository;

import com.lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 29.04.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

}
