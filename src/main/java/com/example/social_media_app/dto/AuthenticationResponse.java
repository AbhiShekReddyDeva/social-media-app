package com.example.social_media_app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationResponse {

    private String accessToken;

    private String refreshToken;
}
