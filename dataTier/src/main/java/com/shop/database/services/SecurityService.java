package com.shop.database.services;

import com.shop.database.entities.Object;

/**
 * <code>SecurityService</code> is an interface for working with users authentication .
 */
public interface SecurityService {
    String findLoggedInUsername();
    Object getUser();
    Object getCart();
    void autologin(String username, String password);
}
