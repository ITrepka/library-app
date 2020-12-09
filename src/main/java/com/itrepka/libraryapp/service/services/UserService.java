package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Role;
import com.itrepka.libraryapp.model.User;
import com.itrepka.libraryapp.repository.UserRepository;
import com.itrepka.libraryapp.service.dto.CreateUserDto;
import com.itrepka.libraryapp.service.dto.UpdateUserDto;
import com.itrepka.libraryapp.service.dto.UserDto;
import com.itrepka.libraryapp.service.exception.UserAlreadyExistException;
import com.itrepka.libraryapp.service.exception.UserNotFoundException;
import com.itrepka.libraryapp.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDtoMapper userDtoMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto getUserById(long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(user -> userDtoMapper.toDto(user))
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + id));
    }

    @Transactional
    public UserDto addNewUser(CreateUserDto createUserDto) throws UserAlreadyExistException {
        boolean isExist = checkIsExist(createUserDto.getEmail());

        if (isExist) {
            throw new UserAlreadyExistException("User already exist");
        }

        User user = userDtoMapper.toModel(createUserDto);
        user.setPenaltyForBooksNotReturnedOnTime(0d);
        //todo role logic
        user.setRole(Role.READER);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userDtoMapper.toDto(savedUser);
    }

    @Transactional
    public UserDto updateUserById(long id, UpdateUserDto updateUserDto) throws UserNotFoundException, UserAlreadyExistException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + id));

        //todo
        user.setPassword(updateUserDto.getPassword());
        user.setPenaltyForBooksNotReturnedOnTime(updateUserDto.getPenaltyForBooksNotReturnedOnTime());
        user.setName(updateUserDto.getName());
        user.setSurname(updateUserDto.getSurname());
        user.setSurname(updateUserDto.getSurname());
        //todo
//        user.getRole(updateUserDto.getRole());

        User savedUser = userRepository.save(user);
        return userDtoMapper.toDto(savedUser);
    }


    public UserDto deleteUserById(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + id));
        userRepository.deleteById(id);
        return userDtoMapper.toDto(user);
    }

    private boolean checkIsExist(String email) {
        return getAllUsers().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }
}
