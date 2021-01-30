package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.service.dto.CreateUserDto;
import com.itrepka.libraryapp.view.dtos.CreateReaderFormDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class CreateReaderFormDtoToCreateUserDto {

    public CreateUserDto toCreateUserMapper(CreateReaderFormDto createReaderFormDto) {
        //todo
        String randomPass = RandomStringUtils.randomAlphabetic(10);
        return CreateUserDto.builder()
                .name(createReaderFormDto.getName())
                .surname(createReaderFormDto.getSurname())
                .email(createReaderFormDto.getEmail())
                .address(createReaderFormDto.getAddress())
                .password(randomPass)
                .build();
    }
}
