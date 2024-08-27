package com.example.softproject1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}") //프로필 조회
    public ProfileDto getProfile(@PathVariable Long profileId) {
        return profileService.getProfileById(profileId);
    }
}