package com.tyrytyry.security;

import com.tyrytyry.model.User;
import com.tyrytyry.model.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}