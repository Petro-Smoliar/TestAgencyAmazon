package com.example.TestAgencyAmazon.mapper;

import com.example.TestAgencyAmazon.config.MapperConfig;
import com.example.TestAgencyAmazon.dto.UserDto;
import com.example.TestAgencyAmazon.dto.UserRegistrationRequestDto;
import com.example.TestAgencyAmazon.models.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserDto toDto(User User);

    User toModel(UserRegistrationRequestDto dto);
}
