package com.bookstoreapi.bookstoreapi.security;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserDetailsRespository userDetailsRespository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetailsRespository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDetailsImplementation.build(user);
    }
}
