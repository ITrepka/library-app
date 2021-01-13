package com.itrepka.libraryapp.view.service;

import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.service.mappers.BookDtoBookViewDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewService {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookDtoBookViewDtoMapper bookViewMapper;

    public List<BookViewDto> getBooksToDisplay() throws AuthorNotFoundException, BookCopyNotFoundException {
        List<BookViewDto> books = new ArrayList<>();

        List<BookDto> booksDto = bookService.getAllBooks();

        for (BookDto bookDto : booksDto) {
            BookViewDto bookViewDto = bookViewMapper.toBookViewDto(bookDto);
            books.add(bookViewDto);
        }

        return books;
    }
}
