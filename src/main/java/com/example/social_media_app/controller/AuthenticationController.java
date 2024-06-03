package com.example.social_media_app.controller;


import com.example.social_media_app.dto.AuthenticationRequest;
import com.example.social_media_app.dto.RegistrationRequest;
import com.example.social_media_app.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegistrationRequest userDto){
        return authenticationService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        return authenticationService.login(request);
    }
}
