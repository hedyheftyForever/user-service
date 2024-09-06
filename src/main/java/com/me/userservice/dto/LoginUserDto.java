package com.me.userservice.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String token;

    private UserDto userDto;
}
