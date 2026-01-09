package com.ratel.auth.user.controller;

import com.ratel.auth.user.dto.UserRequest;
import com.ratel.auth.user.model.User;
import com.ratel.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.addUser(userRequest));
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/update/{id}")
    public User updateUser(@PathVariable(value = "id") Long id,
                           @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }
}
