package com.example.TestAgencyAmazon.service;

import com.example.TestAgencyAmazon.dto.UserDto;
import com.example.TestAgencyAmazon.dto.UserRegistrationRequestDto;

public interface UserService {

    UserDto save(UserRegistrationRequestDto dto);
}
