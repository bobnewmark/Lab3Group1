package com.lab3.service;


import com.lab3.model.User;

import java.util.List;

public interface UserService {
    User getUser(String login);
    User getUserById(int id);
    User addUser(User user);
    void delete(int id);
    List<User> getAll();
    User editUser(User user);
    User editUserWithSha(User user);

}
