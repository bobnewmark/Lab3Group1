package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.services.ObjectService;
import com.shop.database.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <code>SecurityServiceImpl</code> is a class for managing main user actions as log in,
 * finding user in the database, accessing user's shopping cart.
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        java.lang.Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
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
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken){
            return null;
        }
        for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if ("ADMIN".equals(auth.getAuthority())) {
                return null;
            }
        }
        return objectService.findByParent(getUser()).get(0); /*TODO: throw IndexOutOfBoundsException if role Admin*/
    }

    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
