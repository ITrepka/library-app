package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.BorrowingDto;
import com.itrepka.libraryapp.service.dto.UserDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.exception.UserNotFoundException;
import com.itrepka.libraryapp.service.services.BookCopyService;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.service.services.UserService;
import com.itrepka.libraryapp.view.dtos.BorrowingViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingDtoToBorrowingViewDtoMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCopyService bookCopyService;

    public BorrowingViewDto toBorrowingViewDto(BorrowingDto borrowingDto) throws UserNotFoundException, BookCopyNotFoundException, BookNotFoundException {
        LocalDate borrowingDate = borrowingDto.getBorrowingBookDate().toLocalDate();
        LocalDate returnDate = borrowingDto.getReturningBookDate() == null ? null : borrowingDto.getReturningBookDate().toLocalDate();
        Long readerId = borrowingDto.getBorrowingUserId();
        UserDto userDto = userService.getUserById(readerId);
        String reader = userDto.getName() + " " + userDto.getSurname();
        Long bookCopyId = borrowingDto.getBookCopyId();
        BookCopyDto bookCopyDto = bookCopyService.getBookCopyById(bookCopyId);
        Long bookId = bookCopyDto.getBookId();
        BookDto bookDto = bookService.getBookById(bookId);
        String title = bookDto.getTitle();
        return BorrowingViewDto.builder()
                .borrowingId(borrowingDto.getBorrowingId())
                .bookCopyId(bookCopyId)
                .bookId(bookId)
                .readerId(readerId)
                .title(title)
                .reader(reader)
                .borrowingDate(borrowingDate)
                .finalReturnDate(borrowingDate.plusDays(30))
                .returnDate(returnDate)
                .build();
    }
}
