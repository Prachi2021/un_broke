package com.prachi.un_broke.service;

import com.prachi.un_broke.model.CustomUserDetails;
import com.prachi.un_broke.model.User;
import com.prachi.un_broke.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByEmail(username).orElse(null);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new CustomUserDetails(user);
        }
    }
