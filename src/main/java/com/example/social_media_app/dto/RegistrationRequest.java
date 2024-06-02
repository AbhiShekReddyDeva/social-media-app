package com.example.social_media_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String role;
}
