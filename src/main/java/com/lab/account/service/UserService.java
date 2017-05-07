package com.lab.account.service;

import com.lab.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
