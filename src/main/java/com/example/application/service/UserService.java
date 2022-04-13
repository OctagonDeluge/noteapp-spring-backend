package com.example.application.service;

import com.example.application.entity.Role;
import com.example.application.entity.RoleName;
import com.example.application.entity.User;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<?> registration(User user)  {
        if(userRepository.findByEmail(user.getEmail()) == null) {
            Role role = roleRepository.findByName(RoleName.USER);
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(role);

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(userRoles);
            user.setValid(true);
            userRepository.save(user);
            return ResponseEntity.ok("User successfully registered");
        } else {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "User with such email already exists");
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
