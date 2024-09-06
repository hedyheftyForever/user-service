package com.me.userservice.service;

import com.me.userservice.converter.UserConverter;
import com.me.userservice.dto.AuthUserDto;
import com.me.userservice.dto.CreateUserDto;
import com.me.userservice.dto.UserDto;
import com.me.userservice.mapper.UserMapper;
import com.me.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return UserConverter.toUserDto(user);
    }

    public AuthUserDto getAuthUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return UserConverter.toAuthUserDto(user);
    }

    public void createUser(CreateUserDto createUserDto) {
        User user = UserConverter.createUser(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.createUser(user);
    }
}
