package com.example.TestAgencyAmazon.service.impl;

import com.example.TestAgencyAmazon.dto.UserDto;
import com.example.TestAgencyAmazon.dto.UserRegistrationRequestDto;
import com.example.TestAgencyAmazon.exception.RegistrationException;
import com.example.TestAgencyAmazon.mapper.UserMapper;
import com.example.TestAgencyAmazon.models.User;
import com.example.TestAgencyAmazon.repository.UserRepository;
import com.example.TestAgencyAmazon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto save(UserRegistrationRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RegistrationException("Exists email: " + dto.getEmail());
        }
        User newUser = userMapper.toModel(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = userRepository.save(newUser);
        return userMapper.toDto(user);
    }
}
