package com.example.social_media_app.service;

import com.example.social_media_app.modal.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}
