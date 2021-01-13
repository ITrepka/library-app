package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.service.dto.CreateUpdateBookDto;
import com.itrepka.libraryapp.view.dtos.CreateBookFormDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
public class CreateBookFormDtoToCreateUpdateDtoMapper {
    public CreateUpdateBookDto toCreateUpdateDto(CreateBookFormDto createBookFormDto) {
        return CreateUpdateBookDto.builder()
                .title(createBookFormDto.getTitle())
                .pageCount(createBookFormDto.getPageCount())
                .publishedDate(LocalDate.parse(createBookFormDto.getPublishedDate()))
                .longDescription("")
                .shortDescription("")
                .thumbnailUrl("")
                .build();
    }
}
