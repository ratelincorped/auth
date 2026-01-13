package com.ratel.auth.auth.service;

import com.ratel.auth.auth.dto.AuthenticationRequest;
import com.ratel.auth.auth.dto.AuthenticationResponse;
import com.ratel.auth.auth.dto.RefreshRequest;
import com.ratel.auth.auth.dto.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest request);

}
