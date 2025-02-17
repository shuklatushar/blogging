package com.blogging.blogging.services;

import com.blogging.blogging.payloads.UserDto;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer UserId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);

}
