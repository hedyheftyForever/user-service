package com.me.userservice.controller;

import com.me.userservice.dto.AuthUserDto;
import com.me.userservice.dto.LoginUserDto;
import com.me.userservice.dto.UserDto;
import com.me.userservice.service.AuthenticationService;
import com.me.userservice.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginUserDto> login(@RequestBody AuthUserDto authUserDto) {
        if (!authenticationService.authenticate(authUserDto)) {
            log.error("auth failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setToken(JwtUtils.generateToken(authUserDto.getUsername()));
        UserDto userDto = new UserDto();
        userDto.setUsername(authUserDto.getUsername());
        loginUserDto.setUserDto(userDto);
        return ResponseEntity.ok(loginUserDto);
    }
}
