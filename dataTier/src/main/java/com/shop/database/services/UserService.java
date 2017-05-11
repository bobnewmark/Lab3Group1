package com.shop.database.services;


import com.shop.database.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
