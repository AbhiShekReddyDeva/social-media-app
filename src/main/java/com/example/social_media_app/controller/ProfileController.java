package com.example.social_media_app.controller;

import com.example.social_media_app.dto.ProfileDto;
import com.example.social_media_app.modal.Profile;
import com.example.social_media_app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileDto> getProfile() {
        Profile profile = profileService.getProfile();
        ProfileDto profileDTO = new ProfileDto(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getBio(),
                profile.getProfilePictureUrl(),
                profile.getUser().getUsername(),
                profile.getUser().getEmail()
        );
        return ResponseEntity.ok(profileDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDTO) {
        Profile profile = new Profile(
                profileDTO.getId(),
                profileDTO.getFirstName(),
                profileDTO.getLastName(),
                profileDTO.getBio(),
                profileDTO.getProfilePictureUrl(),
                null // Set the user field appropriately
        );
        Profile updatedProfile = profileService.updateProfile(profile);
        ProfileDto updatedProfileDTO = new ProfileDto(
                updatedProfile.getId(),
                updatedProfile.getFirstName(),
                updatedProfile.getLastName(),
                updatedProfile.getBio(),
                updatedProfile.getProfilePictureUrl(),
                updatedProfile.getUser().getUsername(),
                updatedProfile.getUser().getEmail()
        );
        return ResponseEntity.ok(updatedProfileDTO);
    }
}
