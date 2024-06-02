package com.example.social_media_app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {

    private String email;

    private String password;
}
