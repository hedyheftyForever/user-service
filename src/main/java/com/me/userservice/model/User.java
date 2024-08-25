package com.me.userservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;

    private String username;

    private String password;

    private LocalDateTime createTime;

    private String email;

    private String phone;
}
