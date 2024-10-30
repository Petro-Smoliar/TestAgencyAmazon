package com.example.TestAgencyAmazon.security;

import com.example.TestAgencyAmazon.dto.UserLoginRequestDto;
import com.example.TestAgencyAmazon.dto.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authentication(UserLoginRequestDto loginRequestDto);
}
