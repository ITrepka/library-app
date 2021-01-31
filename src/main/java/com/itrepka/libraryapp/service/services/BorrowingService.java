package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.model.Borrowing;
import com.itrepka.libraryapp.model.User;
import com.itrepka.libraryapp.repository.BookCopyRepository;
import com.itrepka.libraryapp.repository.BorrowingRepository;
import com.itrepka.libraryapp.repository.UserRepository;
import com.itrepka.libraryapp.service.dto.BorrowingDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBorrowingDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.exception.BorrowingNotFoundException;
import com.itrepka.libraryapp.service.exception.UserNotFoundException;
import com.itrepka.libraryapp.service.mapper.BorrowingDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private BorrowingDtoMapper borrowingDtoMapper;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private UserRepository userRepository;

    public List<BorrowingDto> getAllBorrowings() {
        return borrowingRepository.findAll().stream()
                .map(borrowing -> borrowingDtoMapper.toDto(borrowing))
                .collect(Collectors.toList());
    }

    @Transactional
    public BorrowingDto getBorrowingById(long id) throws BorrowingNotFoundException {
        return borrowingRepository.findById(id)
                .map(borrowing -> borrowingDtoMapper.toDto(borrowing))
                .orElseThrow(() -> new BorrowingNotFoundException("Not found borrowing with id = " + id));
    }

    @Transactional
    public BorrowingDto addNewBorrowing(CreateUpdateBorrowingDto createUpdateBorrowingDto) throws BookCopyNotFoundException, UserNotFoundException {
        Borrowing borrowing = borrowingDtoMapper.toModel(createUpdateBorrowingDto);
        borrowing.setBorrowingBookDate(OffsetDateTime.now());
        borrowing.setFinalReturningBookDate(OffsetDateTime.now().plusDays(30));
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return borrowingDtoMapper.toDto(savedBorrowing);
    }

    @Transactional
    public BorrowingDto updateBorrowingById(long id, CreateUpdateBorrowingDto createUpdateBorrowingDto) throws BorrowingNotFoundException, BookCopyNotFoundException, UserNotFoundException {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new BorrowingNotFoundException("Not found borrowing with id = " + id));

        BookCopy bookCopy = createUpdateBorrowingDto.getBookCopyId() == null ? null :
                bookCopyRepository.findById(createUpdateBorrowingDto.getBookCopyId())
                        .orElseThrow(() -> new BookCopyNotFoundException("Not Found BookCopy"));

        User user = createUpdateBorrowingDto.getBorrowingUserId() == null ? null :
                userRepository.findById(createUpdateBorrowingDto.getBorrowingUserId())
                        .orElseThrow(() -> new UserNotFoundException("User not found"));

        borrowing.setBookCopy(bookCopy);
        borrowing.setBorrowingUser(user);

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return borrowingDtoMapper.toDto(savedBorrowing);
    }


    public BorrowingDto deleteBorrowingById(long id) throws BorrowingNotFoundException {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new BorrowingNotFoundException("Not found borrowing with id = " + id));
        borrowingRepository.deleteById(id);
        return borrowingDtoMapper.toDto(borrowing);
    }

    public BorrowingDto returnBorrowingById(Long id) throws BorrowingNotFoundException {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new BorrowingNotFoundException("Not found borrowing with id = " + id));
        borrowing.setReturningBookDate(OffsetDateTime.now());
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return borrowingDtoMapper.toDto(savedBorrowing);
    }
}
