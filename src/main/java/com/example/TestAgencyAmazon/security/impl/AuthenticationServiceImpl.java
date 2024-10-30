package com.example.TestAgencyAmazon.security.impl;

import com.example.TestAgencyAmazon.dto.UserLoginRequestDto;
import com.example.TestAgencyAmazon.dto.UserLoginResponseDto;
import com.example.TestAgencyAmazon.security.AuthenticationService;
import com.example.TestAgencyAmazon.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserLoginResponseDto authentication(UserLoginRequestDto loginRequestDto) {
        final Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequestDto.getEmail(),
                                loginRequestDto.getPassword()
                        )
                );
        return new UserLoginResponseDto(
                jwtUtil.generateToken(authentication.getName())
        );
    }
}
