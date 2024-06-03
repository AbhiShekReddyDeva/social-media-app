package com.example.social_media_app.service.impl;

import com.example.social_media_app.modal.Profile;
import com.example.social_media_app.repository.ProfileRepository;
import com.example.social_media_app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getProfile() {
        String username = getAuthenticatedUsername();
        return profileRepository.findByUserUsername(username).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
