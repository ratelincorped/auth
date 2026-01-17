package com.ratel.auth.auth.impl;

import com.ratel.auth.auth.dto.AuthenticationRequest;
import com.ratel.auth.auth.dto.AuthenticationResponse;
import com.ratel.auth.auth.dto.RefreshRequest;
import com.ratel.auth.auth.dto.RegistrationRequest;
import com.ratel.auth.auth.service.AuthenticationService;
import com.ratel.auth.exception.BusinessException;
import com.ratel.auth.exception.ErrorCode;
import com.ratel.auth.security.service.JwtService;
import com.ratel.auth.user.mapper.userMapper;
import com.ratel.auth.user.model.Role;
import com.ratel.auth.user.model.UserCredential;
import com.ratel.auth.user.repository.RoleRepository;
import com.ratel.auth.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
//                        passwordEncoder.encode(request.password())
                )
        );
        UserCredential user = (UserCredential) authentication.getPrincipal();
        assert user != null;
        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        String tokenType = "Bearer";
        return new AuthenticationResponse(accessToken,refreshToken,tokenType);
    }

    @Override
    public void register(RegistrationRequest request) {
        checkEmailExists(request.email());
//        checkPhone(request.phone());
        checkPassword(request.password(), request.confirmPassword());
        Role userRole = roleRepository.findByName("USER")
                .orElse(Role.builder().name("USER")
                        .createdBy("System").build());
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        UserCredential user = userMapper.registrationToUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        userRepository.save(user);

    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        String accessToken = jwtService.refreshAccessToken(request.refreshToken());
        String tokenType = "Bearer";
        return new AuthenticationResponse(request.refreshToken(), accessToken, tokenType);
    }

    private void checkEmailExists(String email) {
        boolean emailExists = userRepository.existsByEmailIgnoreCase(email);
        if (emailExists) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS, email);
        }
    }

    private void checkPhone(String phone) {
        //TODO: implement number check
    }

    private void checkPassword(String password, String confirmPassword) {
        if (confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
    }

}
