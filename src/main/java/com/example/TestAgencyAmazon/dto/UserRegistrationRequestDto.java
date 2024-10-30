package com.example.TestAgencyAmazon.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
            message = "Password must contain at least one lowercase letter, "
                    + "one uppercase letter, one digit, and one special character.")
    private String password;
    @NotBlank
    private String repeatPassword;

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordsMatching() {
        return password != null && password.equals(repeatPassword);
    }
}
