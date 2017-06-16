package com.shop.database.services;

import com.shop.database.entities.Object;

public interface SecurityService {
    String findLoggedInUsername();
    Object getUser();
    Object getCart();
    void autologin(String username, String password);
}
