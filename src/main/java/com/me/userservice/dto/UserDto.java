package com.me.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String username;

    private String email;

    private String phone;

    private LocalDateTime createTime;
}
