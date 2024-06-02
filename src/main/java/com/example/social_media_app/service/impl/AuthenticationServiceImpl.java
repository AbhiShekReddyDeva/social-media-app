package com.example.social_media_app.service.impl;


import com.example.social_media_app.dto.AuthenticationRequest;
import com.example.social_media_app.dto.AuthenticationResponse;
import com.example.social_media_app.dto.RegistrationRequest;
import com.example.social_media_app.enums.Role;
import com.example.social_media_app.exceptions.DuplicateUserException;
import com.example.social_media_app.modal.User;
import com.example.social_media_app.repository.UserRepository;
import com.example.social_media_app.service.AuthenticationService;
import com.example.social_media_app.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createUser(RegistrationRequest request) {
        Optional<User> existingStudent = userRepository.findByEmail(request.getEmail());
        if (existingStudent.isEmpty()) {
            User user = User.builder()
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            if(request.getRole().equalsIgnoreCase("user")){
                user.setRole(Role.USER);
            }
            else {
                user.setRole(Role.ADMIN);
            }

            userRepository.save(user);

            return ResponseEntity.ok("User Registered successfully");

        } else {
            throw new DuplicateUserException("User with email " + request.getEmail() + " already exist");
        }
    }

    public ResponseEntity<?> login(AuthenticationRequest request){
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new BadCredentialsException("Invalid email or password");
        }
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
        return ResponseEntity.ok(response);
    }
}
