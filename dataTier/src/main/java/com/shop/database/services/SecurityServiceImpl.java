package com.shop.database.services;

import com.shop.database.entities.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <code>SecurityServiceImpl</code> is a class for managing main user actions as log in, sign in,
 * finding user in the database, accessing user's shopping cart.
 */
@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ObjectService objectService;
    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
        java.lang.Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    @Override
    public Object getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return objectService.findByNameAttrAndObjectType("user", "login", username).get(0);
    }

    @Override
    public Object getCart() {
        Object cart = null;
        try {
            cart = objectService.findByParent(getUser()).get(0);
        } catch (Exception ignored) {
        }
        return cart;
    }

    @Override
    public void autologin(String username, String password) {
        System.out.println("username-----"+ username + ", password--------"+ password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("1");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        System.out.println("2");
        //authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        System.out.println("3");
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            System.out.println("4");
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("5");
        }
    }
}
