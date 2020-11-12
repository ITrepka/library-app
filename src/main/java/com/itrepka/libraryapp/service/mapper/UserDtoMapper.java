package com.itrepka.libraryapp.service.mapper;

import com.itrepka.libraryapp.model.Borrowing;
import com.itrepka.libraryapp.model.User;
import com.itrepka.libraryapp.service.dto.CreateUserDto;
import com.itrepka.libraryapp.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper {
    public UserDto toDto(User user) {
        List<Long> borrowingsIds = user.getBorrowings() == null ? null :
                user.getBorrowings().stream().map(Borrowing::getBorrowingId).collect(Collectors.toList());

        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress())
                .penaltyForBooksNotReturnedOnTime(user.getPenaltyForBooksNotReturnedOnTime())
                .role(user.getRole().toString())
                .borrowingsIds(borrowingsIds)
                .build();
    }

    public User toModel(CreateUserDto createUserDto) {
        return User.builder()
                .userId(null)
                .penaltyForBooksNotReturnedOnTime(null)
                .borrowings(null)
                .password(null)
                .role(null)
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .address(createUserDto.getAddress())
                .email(createUserDto.getEmail())
                .build();
    }
}
