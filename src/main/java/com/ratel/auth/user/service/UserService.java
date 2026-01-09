package com.ratel.auth.user.service;

import com.ratel.auth.user.dto.UserRequest;
import com.ratel.auth.user.model.User;
import com.ratel.auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public User addUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstname(userRequest.firstname());
        user.setLastname(userRequest.lastname());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+ userId));
        user.setFirstname(userRequest.firstname());
        user.setLastname(userRequest.lastname());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        return userRepository.save(user);
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
}
