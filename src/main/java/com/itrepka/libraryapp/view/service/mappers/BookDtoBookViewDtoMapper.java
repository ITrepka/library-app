package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.service.dto.AuthorDto;
import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.services.AuthorService;
import com.itrepka.libraryapp.service.services.BookCopyService;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDtoBookViewDtoMapper {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private AuthorService authorService;

    public BookViewDto toBookViewDto(BookDto bookDto) throws BookCopyNotFoundException, AuthorNotFoundException {
        List<Long> bookCopiesIds = bookDto.getBookCopiesIds();
        List<BookCopyDto> bookCopyDtos = new ArrayList<>();
        for (Long bookCopiesId : bookCopiesIds) {
            BookCopyDto bookCopyDto = bookCopyService.getBookCopyById(bookCopiesId);
            bookCopyDtos.add(bookCopyDto);
        }

        Long currentlyBorrowed = bookCopyDtos.stream().filter(BookCopyDto::getIsAvailableToBorrow).count();
        Long availableToBorrow = bookCopyDtos.stream().filter(bookCopyDto -> !bookCopyDto.getIsAvailableToBorrow()).count();

        List<String> authorsFullNames = new ArrayList<>();

        List<Long> authorsIds = bookDto.getAuthorsIds();

        for (Long authorsId : authorsIds) {
            AuthorDto authorDto = authorService.getAuthorById(authorsId);

            authorsFullNames.add(authorDto.getName() + " " + authorDto.getSurname());
        }

        return BookViewDto.builder()
                .bookId(bookDto.getBookId())
                .pageCount(bookDto.getPageCount())
                .title(bookDto.getTitle())
                .publishedDate(bookDto.getPublishedDate().toLocalDate())
                .copiesNumber(bookDto.getBookCopiesIds().size())
                .currentlyBorrowed(currentlyBorrowed)
                .availableToBorrow(availableToBorrow)
                .authorsFullNames(authorsFullNames)
                .build();
    }
}
