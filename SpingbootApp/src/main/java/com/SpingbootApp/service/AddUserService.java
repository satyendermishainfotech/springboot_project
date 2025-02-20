package com.SpingbootApp.service;

import com.SpingbootApp.entity.User;
import com.SpingbootApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AddUserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        // Hash the password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); // Save the user with the hashed password
    }
}
