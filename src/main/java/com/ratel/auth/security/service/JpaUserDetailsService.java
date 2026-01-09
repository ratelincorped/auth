package com.ratel.auth.security.service;

import com.ratel.auth.security.model.SecurityUser;
import com.ratel.auth.user.dto.ChangePasswordRequest;
import com.ratel.auth.user.dto.UserUpdateRequest;
import com.ratel.auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmailIgnoreCase(email)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found with email: "+ email));
    }

    void updateProfileInfo(UserUpdateRequest request, String userId) {};

    void changePassword(ChangePasswordRequest request, String userId) {};

    void deactivateAccount(String userId) {};

    void reactivateAccount(String userId) {};

    void deleteAccount(String userId) {};

}
