package com.example.social_media_app.service.impl;

import com.example.social_media_app.exceptions.UserNotFoundException;
import com.example.social_media_app.modal.User;
import com.example.social_media_app.repository.UserRepository;
import com.example.social_media_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            user.setProfile(updatedUser.getProfile());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
