package com.example.TestAgencyAmazon.controller;

import com.example.TestAgencyAmazon.dto.UserDto;
import com.example.TestAgencyAmazon.dto.UserLoginRequestDto;
import com.example.TestAgencyAmazon.dto.UserLoginResponseDto;
import com.example.TestAgencyAmazon.dto.UserRegistrationRequestDto;
import com.example.TestAgencyAmazon.security.AuthenticationService;
import com.example.TestAgencyAmazon.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Valid
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/auth/registration")
    public UserDto register(@Valid @RequestBody UserRegistrationRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PostMapping("/auth/login")
    public UserLoginResponseDto login(@Valid @RequestBody UserLoginRequestDto request) {
        return authenticationService.authentication(request);
    }
}
