package com.ratel.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(
        String refreshToken,
        String accessToken,
        String tokenType
) {
}
