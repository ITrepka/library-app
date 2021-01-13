package com.itrepka.libraryapp.service.mapper;

import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.model.Borrowing;
import com.itrepka.libraryapp.repository.BookRepository;
import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookCopyDto;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyDtoMapper {
    @Autowired
    private BookRepository bookRepository;

    public BookCopyDto toDto(BookCopy bookCopy) {
        Long bookId = bookCopy.getBook() == null ? null : bookCopy.getBook().getBookId();

        List<Long> borrowingsIds = bookCopy.getBorrowings() == null ? null :
                bookCopy.getBorrowings().stream().map(Borrowing::getBorrowingId).collect(Collectors.toList());

        return BookCopyDto.builder()
                .bookCopyId(bookCopy.getBookCopyId())
                .bookId(bookId)
                .borrowingsIds(borrowingsIds)
                .isAvailableToBorrow(bookCopy.getIsAvailableToBorrow())
                .build();
    }

    public BookCopy toModel(CreateUpdateBookCopyDto createUpdateBookCopyDto) throws BookNotFoundException {
        Book book = bookRepository.findById(createUpdateBookCopyDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Not Found Book with id = " + createUpdateBookCopyDto.getBookId()));

        return BookCopy.builder()
                .bookCopyId(null)
                .book(book)
                .borrowings(new ArrayList<>())
                .isAvailableToBorrow(null)
                .build();
    }
}
