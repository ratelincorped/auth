package com.ratel.auth.user.dto;

public record ChangePasswordRequest(
        String oldPassword,
        String newPassword,
        String confirmPassword
) {
}
