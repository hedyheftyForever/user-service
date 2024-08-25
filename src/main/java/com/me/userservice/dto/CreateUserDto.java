package com.me.userservice.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;

    private String password;

    private String phone;

    private String email;
}
