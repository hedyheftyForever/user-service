package com.me.userservice.converter;

import com.me.userservice.dto.CreateUserDto;
import com.me.userservice.dto.UserDto;
import com.me.userservice.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserConverter {
    public static UserDto toUserDto(User user) {
        if (user == null) {
            throw new RuntimeException("user is null");
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setCreateTime(user.getCreateTime());
        return userDto;
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
