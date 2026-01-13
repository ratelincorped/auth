package com.ratel.auth.user.mapper;

import com.ratel.auth.auth.dto.RegistrationRequest;
import com.ratel.auth.user.model.UserCredential;
import org.springframework.stereotype.Component;

@Component
public class userMapper {
/*
    public void mergeUserInfo(User savedUser, UserUpdateRequest request) {
        if (StringUtils.isNotBlank(request.firstname())
                && !savedUser.getFirstname().equals(request.firstname())
        ) {
            savedUser.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())
                && !savedUser.getLastname().equals(request.lastname())
        ) {
            savedUser.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.phone())
                && !savedUser.getPhone().equals(request.phone())
        ) {
            savedUser.setPhone(request.phone());
        }
        if (request.dateOfBirth() != null
                && !savedUser.getDateOfBirth().equals(request.dateOfBirth())
        ) {
            savedUser.setDateOfBirth(request.dateOfBirth());
        }
    }
*/

    public static UserCredential registrationToUser(RegistrationRequest request) {
        return UserCredential.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phone(request.phone())
                .password(request.password())
                .build();
    }

}
