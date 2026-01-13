package com.ratel.auth.user.repository;

import com.ratel.auth.user.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}
