package com.itrepka.libraryapp.view.service;

import com.itrepka.libraryapp.service.dto.*;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.service.services.AuthorBookService;
import com.itrepka.libraryapp.service.services.AuthorService;
import com.itrepka.libraryapp.service.services.BookCopyService;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.dtos.CreateBookFormDto;
import com.itrepka.libraryapp.view.service.mappers.BookDtoBookViewDtoMapper;
import com.itrepka.libraryapp.view.service.mappers.CreateBookFormDtoToCreateUpdateDtoMapper;
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
    @Autowired
    private CreateBookFormDtoToCreateUpdateDtoMapper bookFormToCreateDtoMapper;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorBookService authorBookService;
    @Autowired
    private BookCopyService bookCopyService;

    public List<BookViewDto> getBooksToDisplay() throws AuthorNotFoundException, BookCopyNotFoundException {
        List<BookViewDto> books = new ArrayList<>();

        List<BookDto> booksDto = bookService.getAllBooks();

        for (BookDto bookDto : booksDto) {
            BookViewDto bookViewDto = bookViewMapper.toBookViewDto(bookDto);
            books.add(bookViewDto);
        }

        return books;
    }

    public List<BookCopyDto> addBookToDbAndCreateCopies(CreateBookFormDto createBookFormDto) throws BookAlreadyExistException, AuthorAlreadyExistException, AuthorNotFoundException, BookNotFoundException {
        CreateUpdateBookDto createBookDto = bookFormToCreateDtoMapper.toCreateUpdateDto(createBookFormDto);
        BookDto bookDto = bookService.addNewBook(createBookDto);

        String[] authorsArr = createBookFormDto.getAuthors().split(",");
        for (String authorFullName : authorsArr) {
            CreateUpdateAuthorDto createUpdateAuthorDto = new CreateUpdateAuthorDto();
            createUpdateAuthorDto.setFullName(authorFullName);
            AuthorDto authorDto = authorService.addNewAuthor(createUpdateAuthorDto);
            CreateAuthorBookDto createAuthorBookDto = new CreateAuthorBookDto(authorDto.getAuthorId(), bookDto.getBookId());
            authorBookService.addAuthorToBook(createAuthorBookDto);
        }

        List<BookCopyDto> bookCopyDtosList = new ArrayList<>();
        Integer copiesNumber = createBookFormDto.getCopiesNumber();
        for (int i = 0; i < copiesNumber; i++) {
            CreateUpdateBookCopyDto createUpdateBookCopyDto = new CreateUpdateBookCopyDto(bookDto.getBookId(), true);
            BookCopyDto bookCopyDto = bookCopyService.addNewBookCopy(createUpdateBookCopyDto);
            bookCopyDtosList.add(bookCopyDto);
        }

        return bookCopyDtosList;
    }
}
