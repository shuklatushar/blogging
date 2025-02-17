package com.blogging.blogging.security;

import com.blogging.blogging.entity.User;
import com.blogging.blogging.exceptions.ResourceNotFoundException;
import com.blogging.blogging.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user","username : "+username,0));

        return user;
    }
}
