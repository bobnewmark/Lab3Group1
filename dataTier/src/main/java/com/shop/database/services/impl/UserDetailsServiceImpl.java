package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.entities.Parameter;
import com.shop.database.repositories.ObjectRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * <code>UserDetailServiceImpl</code> is a service for users authentication by their unique username.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ObjectRepository objectRepository;

    private final static Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    /**
     * Loads user by his unique username for autologin.
     *
     * @param username unique username/login
     * @return User object with all needed details
     * @throws UsernameNotFoundException in case when current username
     *                                   cannot be found in database
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Object user = null;
        try {
            user = objectRepository.findByAttrAndObjectType("user", "login", username).get(0);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Couldn't find user for autologin, creating new one, exception: ", e);
            user = new Object();
        }
        for (Parameter par : user.getParameters()) {
            user.getMapParameters().put(par.getAttribute().getName(), par);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getMapParameters().get("role").getValue()));
        return new org.springframework.security.core.userdetails.User(user.getMapParameters().get("login").getValue(), user.getMapParameters().get("password").getValue(), grantedAuthorities);
    }
}
