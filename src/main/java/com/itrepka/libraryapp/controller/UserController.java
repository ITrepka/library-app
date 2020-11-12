package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.CreateUserDto;
import com.itrepka.libraryapp.service.dto.UpdateUserDto;
import com.itrepka.libraryapp.service.dto.UserDto;
import com.itrepka.libraryapp.service.exception.UserAlreadyExistException;
import com.itrepka.libraryapp.service.exception.UserNotFoundException;
import com.itrepka.libraryapp.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto addNewUser(@RequestBody CreateUserDto createUserDto) throws UserAlreadyExistException {
        return userService.addNewUser(createUserDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUserById(@PathVariable long id, @RequestBody UpdateUserDto updateUserDto) throws UserNotFoundException, UserAlreadyExistException {
        return userService.updateUserById(id, updateUserDto);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUserById(@PathVariable long id) throws UserNotFoundException {
        return userService.deleteUserById(id);
    }
}
