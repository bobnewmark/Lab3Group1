package com.shop.database.services;

import com.shop.database.entities.Object;

public interface SecurityService {
    String findLoggedInUsername();
    Object getUser();
    void autologin(String username, String password);
}
