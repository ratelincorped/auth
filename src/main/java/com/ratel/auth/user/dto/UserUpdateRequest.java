package com.ratel.auth.user.dto;

import java.time.LocalDateTime;

public record UserUpdateRequest(
        String firstname,
        String lastname,
        String phone,
        LocalDateTime dateOfBirth
) {
}
