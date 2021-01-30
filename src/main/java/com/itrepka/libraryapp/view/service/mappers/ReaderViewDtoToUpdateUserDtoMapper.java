package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.service.dto.UpdateUserDto;
import com.itrepka.libraryapp.view.dtos.ReaderViewDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderViewDtoToUpdateUserDtoMapper {
    public UpdateUserDto toUpdateDto(ReaderViewDto updateReaderDto) {
        return UpdateUserDto.builder()
                .name(updateReaderDto.getName())
                .surname(updateReaderDto.getSurname())
                .address(updateReaderDto.getAddress())
                .penaltyForBooksNotReturnedOnTime(updateReaderDto.getPenalty())
                .build();
    }
}
