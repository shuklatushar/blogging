package com.blogging.blogging.controller;


import com.blogging.blogging.payloads.ApiResponse;
import com.blogging.blogging.payloads.UserDto;
import com.blogging.blogging.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //post-create
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
       UserDto createdUser=userService.createUser(userDto);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //put-update
     @PutMapping("/{userId}")
     public ResponseEntity<UserDto> updateUser(@PathVariable(name="userId") Integer userId,@Valid @RequestBody UserDto userDto){

             UserDto updatedUser= userService.updateUser(userDto,userId);
             return new ResponseEntity<>(updatedUser,HttpStatus.OK);
     }
     //Get- get user by Id

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Integer userId){
         UserDto userDto=userService.getUserById(userId);
         return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    //Get ALL user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
         List<UserDto> allUsers=userService.getAllUsers();
         return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
         userService.deleteUser(id);
         return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted",true),HttpStatus.OK);

    }
}
