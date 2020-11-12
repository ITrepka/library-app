package com.itrepka.libraryapp.service.mapper;

import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.model.Borrowing;
import com.itrepka.libraryapp.model.User;
import com.itrepka.libraryapp.repository.BookCopyRepository;
import com.itrepka.libraryapp.repository.UserRepository;
import com.itrepka.libraryapp.service.dto.BorrowingDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBorrowingDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingDtoMapper {
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private UserRepository userRepository;

    public BorrowingDto toDto(Borrowing borrowing) {
        Long bookCopyId = borrowing.getBookCopy() == null ? null : borrowing.getBookCopy().getBookCopyId();
        Long userId = borrowing.getBorrowingUser() == null ? null : borrowing.getBorrowingUser().getUserId();

        return BorrowingDto.builder()
                .borrowingId(borrowing.getBorrowingId())
                .bookCopyId(bookCopyId)
                .borrowingUserId(userId)
                .borrowingBookDate(borrowing.getBorrowingBookDate())
                .returningBookDate(borrowing.getReturningBookDate())
                .finalReturningBookDate(borrowing.getFinalReturningBookDate())
                .build();
    }

    public Borrowing toModel(CreateUpdateBorrowingDto createUpdateBorrowingDto) throws BookCopyNotFoundException, UserNotFoundException {
        BookCopy bookCopy = createUpdateBorrowingDto.getBookCopyId() == null ? null :
                bookCopyRepository.findById(createUpdateBorrowingDto.getBookCopyId())
                        .orElseThrow(() -> new BookCopyNotFoundException("Not Found BookCopy"));

        User user = createUpdateBorrowingDto.getBorrowingUserId() == null ? null :
                userRepository.findById(createUpdateBorrowingDto.getBorrowingUserId())
                        .orElseThrow(() -> new UserNotFoundException("User not found"));

        return Borrowing.builder()
                .borrowingId(null)
                .borrowingBookDate(null)
                .returningBookDate(null)
                .finalReturningBookDate(null)
                .bookCopy(bookCopy)
                .borrowingUser(user)
                .build();
    }

}
