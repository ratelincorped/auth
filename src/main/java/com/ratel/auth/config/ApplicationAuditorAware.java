package com.ratel.auth.config;

import com.ratel.auth.user.model.UserCredential;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
            || authentication instanceof AnonymousAuthenticationToken
        ) {
        return Optional.empty();
        }
        UserCredential user = (UserCredential) authentication.getPrincipal();
        assert user != null;
        return Optional.ofNullable(user.getId());
    }
}
