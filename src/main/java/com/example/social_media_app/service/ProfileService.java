package com.example.social_media_app.service;

import com.example.social_media_app.modal.Profile;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Profile getProfile();

    Profile updateProfile(Profile profile);
}
