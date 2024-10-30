package com.example.TestAgencyAmazon.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Valid
@Validated
public class UserLoginRequestDto {
    @Email
    @NotEmpty
    private String email;
    private String password;
}
