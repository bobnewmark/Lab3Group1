package com.shop.database.services;


import com.shop.database.entities.Parameter;
import com.shop.database.entities.Object;
import com.shop.database.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private ObjectRepository objectRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Object user = null;
        try {
            user = objectRepository.findByIdAttr("user", "login", username).get(0);
        }catch(ArrayIndexOutOfBoundsException e){
            user = new Object();
        }
        for(Parameter p: user.getParameters()){
            user.getMapParameters().put(p.getAttribute().getName(), p);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getMapParameters().get("role").getValue()));

        return new org.springframework.security.core.userdetails.User(user.getMapParameters().get("login").getValue(), user.getMapParameters().get("password").getValue(), grantedAuthorities);
    }
}
