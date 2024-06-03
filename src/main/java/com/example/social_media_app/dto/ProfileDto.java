package com.example.social_media_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private String profilePictureUrl;
    private String username;
    private String email;
}
