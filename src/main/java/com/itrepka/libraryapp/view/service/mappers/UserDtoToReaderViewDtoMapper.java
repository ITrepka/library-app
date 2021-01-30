package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.service.dto.UserDto;
import com.itrepka.libraryapp.view.dtos.ReaderViewDto;
import org.springframework.stereotype.Service;

@Service
public class UserDtoToReaderViewDtoMapper {
    public ReaderViewDto toReaderViewDto(UserDto reader) {
        return ReaderViewDto.builder()
                .readerId(reader.getUserId())
                .name(reader.getName())
                .surname(reader.getSurname())
                .email(reader.getEmail())
                .address(reader.getAddress())
                .penalty(reader.getPenaltyForBooksNotReturnedOnTime())
                .build();
    }
}
