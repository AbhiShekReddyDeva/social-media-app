package com.example.social_media_app.service;


import com.example.social_media_app.dto.AuthenticationRequest;
import com.example.social_media_app.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<?> createUser(RegistrationRequest userDto);

    ResponseEntity<?> login(AuthenticationRequest request);
}
