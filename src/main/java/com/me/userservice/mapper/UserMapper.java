package com.me.userservice.mapper;

import com.me.userservice.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserById(String id);

    void createUser(User user);
}
