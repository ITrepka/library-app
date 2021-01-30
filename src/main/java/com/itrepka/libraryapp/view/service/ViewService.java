package com.itrepka.libraryapp.view.service;

import com.itrepka.libraryapp.model.Role;
import com.itrepka.libraryapp.service.dto.*;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.service.services.*;
import com.itrepka.libraryapp.view.dtos.*;
import com.itrepka.libraryapp.view.service.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoToReaderViewDtoMapper readerViewMapper;
    @Autowired
    private CreateReaderFormDtoToCreateUserDto readerFormToCreateUserMapper;
    @Autowired
    private ReaderViewDtoToUpdateUserDtoMapper readerViewDtoToUpdateUserDtoMapper;

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
            BookCopyDto bookCopyDto = addBookCopyToDb(bookDto.getBookId());
            bookCopyDtosList.add(bookCopyDto);
        }

        return bookCopyDtosList;
    }

    private BookCopyDto addBookCopyToDb(Long bookId) throws BookNotFoundException {
        CreateUpdateBookCopyDto createUpdateBookCopyDto = new CreateUpdateBookCopyDto(bookId, true);
        return bookCopyService.addNewBookCopy(createUpdateBookCopyDto);
    }

    public UpdateBookFormDto getPreparedUpdateBookFormDto(Long id) throws BookNotFoundException, AuthorNotFoundException {
        BookDto bookDto = bookService.getBookById(id);
        List<Long> authorsIds = bookDto.getAuthorsIds();
        StringBuilder authorsFullNames = new StringBuilder();

        for (Long authorsId : authorsIds) {
            String fullName = authorService.getAuthorById(authorsId).getFullName();
            authorsFullNames.append(fullName).append(",");
        }

        String authors = authorsFullNames.toString().substring(0, authorsFullNames.toString().length() - 1);

        UpdateBookFormDto updateBookFormDto = new UpdateBookFormDto();
        updateBookFormDto.setBookId(bookDto.getBookId());
        updateBookFormDto.setAuthors(authors);
        updateBookFormDto.setPageCount(bookDto.getPageCount());
        updateBookFormDto.setTitle(bookDto.getTitle());
        updateBookFormDto.setPublishedDate(bookDto.getPublishedDate().toString());
        updateBookFormDto.setNewCopies(0);

        return updateBookFormDto;
    }

    public void updateBook(UpdateBookFormDto updateBookFormDto) throws BookNotFoundException, AuthorAlreadyExistException, AuthorNotFoundException {
        CreateUpdateBookDto createUpdateBookDto = new CreateUpdateBookDto();

        createUpdateBookDto.setTitle(updateBookFormDto.getTitle());
        createUpdateBookDto.setPageCount(updateBookFormDto.getPageCount());
        createUpdateBookDto.setPublishedDate(LocalDate.parse(updateBookFormDto.getPublishedDate()));

        String authors = updateBookFormDto.getAuthors();

        String[] authorsArr = authors.split(",");
        for (String authorFullName : authorsArr) {
            boolean authorExistAlready = authorService.getAllAuthors().stream()
                    .anyMatch(authorDto -> authorDto.getFullName().equalsIgnoreCase(authorFullName));

            if (!authorExistAlready) {
                CreateUpdateAuthorDto createUpdateAuthorDto = new CreateUpdateAuthorDto();
                createUpdateAuthorDto.setFullName(authorFullName);
                AuthorDto authorDto = authorService.addNewAuthor(createUpdateAuthorDto);
                CreateAuthorBookDto createAuthorBookDto = new CreateAuthorBookDto(authorDto.getAuthorId(), updateBookFormDto.getBookId());
                authorBookService.addAuthorToBook(createAuthorBookDto);
            }
        }


        Integer newCopies = updateBookFormDto.getNewCopies();

        for (int i = 0; i < newCopies; i++) {
            addBookCopyToDb(updateBookFormDto.getBookId());
        }
    }

    public List<ReaderViewDto> getReadersToDisplay() {
        List<UserDto> allUsers = userService.getAllUsers();
        List<ReaderViewDto> readers = new ArrayList<>();

        allUsers = allUsers.stream()
                .filter(userDto -> userDto.getRole().equals(Role.READER.name()))
                .collect(Collectors.toList());

        for (UserDto reader : allUsers) {
            ReaderViewDto readerView = readerViewMapper.toReaderViewDto(reader);
            readers.add(readerView);
        }

        return readers;
    }

    public void addReaderToDb(CreateReaderFormDto createReaderFormDto) throws UserAlreadyExistException {
        CreateUserDto createUserDto = readerFormToCreateUserMapper.toCreateUserMapper(createReaderFormDto);
        userService.addNewUser(createUserDto);
    }


    public ReaderViewDto getPreparedUpdateReaderDto(Long id) throws UserNotFoundException {
        UserDto userDto = userService.getUserById(id);
        return readerViewMapper.toReaderViewDto(userDto);
    }

    public void updateReader(ReaderViewDto updateReaderDto) throws UserNotFoundException, UserAlreadyExistException {
        UpdateUserDto updateUserDto = readerViewDtoToUpdateUserDtoMapper.toUpdateDto(updateReaderDto);

        userService.updateUserById(updateReaderDto.getReaderId(), updateUserDto);
    }
}
