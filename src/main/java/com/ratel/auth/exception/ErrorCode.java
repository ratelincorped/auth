package com.ratel.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND","User not found with id %s", HttpStatus.NOT_FOUND),
    CHANGE_PASSWORD_MISSMATCH("CHANGE_PASSWORD_MISSMATCH", "New and confirm password are not the same",
            HttpStatus.BAD_REQUEST),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD","Incorrect current password provided",HttpStatus.BAD_REQUEST),
    USER_ACCOUNT_ENABLED("USER_ACCOUNT_ENABLED","User with email %s and id %s is enabled",HttpStatus.BAD_REQUEST),
    USER_ACCOUNT_DISABLED("USER_ACCOUNT_DISABLED","User with email %s and id %s is disabled",HttpStatus.BAD_REQUEST),
    TOKEN_IS_INVALID("TOKEN_IS_INVALID","%s jwt token is invalid",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "email %s already exists", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH", "password mis-match error", HttpStatus.BAD_REQUEST),
    ERR_USER_DISABLE("ERR_USER_DISABLE","User is Disabled", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS("BAD_CREDENTIALS","Username and/or password provided is incorrect", HttpStatus.UNAUTHORIZED),
    INTRNAL_EXCEPTION("INTRNAL_EXCEPTION","Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND","Entity not found",HttpStatus.NOT_FOUND);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

}
