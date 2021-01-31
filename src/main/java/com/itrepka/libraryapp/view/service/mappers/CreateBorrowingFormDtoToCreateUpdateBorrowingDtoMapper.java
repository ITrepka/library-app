package com.itrepka.libraryapp.view.service.mappers;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBorrowingDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.services.BookCopyService;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.dtos.CreateBorrowingFormDto;
import com.itrepka.libraryapp.view.dtos.ReaderViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateBorrowingFormDtoToCreateUpdateBorrowingDtoMapper {
    @Autowired
    private BookCopyService bookCopyService;

    public CreateUpdateBorrowingDto toCreateUpdateBorrowingDto(CreateBorrowingFormDto createBorrowingFormDto) throws BookCopyNotFoundException {
        ReaderViewDto reader = createBorrowingFormDto.getReader();
        BookViewDto book = createBorrowingFormDto.getBook();

        List<BookCopyDto> allBookCopies = bookCopyService.getAllBookCopies();
        BookCopyDto bookCopy = allBookCopies.stream().filter(BookCopyDto::getIsAvailableToBorrow)
                .filter(bookCopyDto -> bookCopyDto.getBookId().equals(book.getBookId()))
                .findFirst()
                .orElseThrow(() -> new BookCopyNotFoundException("Not found available book copy"));

        return CreateUpdateBorrowingDto.builder()
                .bookCopyId(bookCopy.getBookCopyId())
                .borrowingUserId(reader.getReaderId())
                .build();
    }
}
