package com.ratel.auth.user.dto;

public record UserRequest(String firstname,
                          String lastname,
                          String email,
                          String password,
                          String roles) {
}
