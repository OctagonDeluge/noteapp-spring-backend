package com.example.application.security;

import com.example.application.entity.User;
import com.example.application.repository.UserRepository;
import com.example.application.service.UserService;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new SecurityUser(user.getEmail(), user.getPassword(), user.isValid(), user.getRoles());
    }
}
