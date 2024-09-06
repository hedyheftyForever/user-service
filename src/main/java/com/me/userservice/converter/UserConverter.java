package com.me.userservice.converter;

import com.me.userservice.dto.AuthUserDto;
import com.me.userservice.dto.CreateUserDto;
import com.me.userservice.dto.UserDto;
import com.me.userservice.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserConverter {
    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setCreateTime(user.getCreateTime());
        return userDto;
    }

    public static AuthUserDto toAuthUserDto(User user) {
        if (user == null) {
            return null;
        }
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUsername(user.getUsername());
        authUserDto.setPassword(user.getPassword());
        return authUserDto;
    }

    public static User createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(LocalDateTime.now());
        user.setUsername(createUserDto.getUsername());
        user.setPassword(createUserDto.getPassword());
        user.setPhone(createUserDto.getPhone());
        user.setEmail(createUserDto.getEmail());
        return user;
    }
}
