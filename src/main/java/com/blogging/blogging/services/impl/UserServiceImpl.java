package com.blogging.blogging.services.impl;

import com.blogging.blogging.entity.User;
import com.blogging.blogging.exceptions.ResourceNotFoundException;
import com.blogging.blogging.payloads.UserDto;
import com.blogging.blogging.repositories.UserRepo;
import com.blogging.blogging.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

            User user= this.userDtoTouser(userDto);
            User savedUser=userRepo.save(user);

        return this.userTouserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
         User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
         user.setAbout(userDto.getAbout());
         user.setEmail(userDto.getEmail());
         user.setName(userDto.getName());
         user.setPassword(userDto.getPassword());
         User updatedUser=userRepo.save(user);
         return this.userTouserDto(updatedUser);

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        return this.userTouserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepo.findAll();
        List<UserDto> userDtoList=users.stream().map(user -> userTouserDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        userRepo.deleteById(userId);
    }

    private UserDto userTouserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

    private User userDtoTouser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
