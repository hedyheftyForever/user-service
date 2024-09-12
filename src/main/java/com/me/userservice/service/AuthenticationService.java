package com.me.userservice.service;

import com.me.userservice.dto.AuthUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(AuthUserDto authUserDto) {
        AuthUserDto localUser = userService.getAuthUserByUsername(authUserDto.getUsername());
        if (localUser == null) {
            log.info("cannot find user by name: {}", authUserDto.getUsername());
            return false;
        }

        return passwordEncoder.matches(authUserDto.getPassword(), localUser.getPassword());
    }
}
