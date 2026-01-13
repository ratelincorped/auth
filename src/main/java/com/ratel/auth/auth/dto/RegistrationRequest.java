package com.ratel.auth.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record RegistrationRequest(
        @NotBlank
        @Size(min = 1, max = 20)
        @Pattern(regexp = "^[\\p{L}'-]+$")
        String firstname,

        @NotBlank
        @Size(min = 1, max = 20)
        @Pattern(regexp = "^[\\p{L}'-]+$")
        String lastname,

        @NotBlank
        @Email
//        TODO: email validation
//        @NonDisposableEmail
        String email,

        @NotBlank
        @Pattern(regexp = "^\\+[0-9]{10,13}$")
        String phone,

        @NotBlank
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$")
        String password,

        @NotBlank
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$")
        String confirmPassword
) {
}
