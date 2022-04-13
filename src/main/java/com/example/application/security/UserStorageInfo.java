package com.example.application.security;

import com.example.application.entity.User;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserStorageInfo implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserStorageInfo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return new SecurityUser(user.getEmail(), user.getPassword(), user.isValid(), user.getRoles());
        } else {
            throw new UsernameNotFoundException("User with such email not found");
        }
    }
}
