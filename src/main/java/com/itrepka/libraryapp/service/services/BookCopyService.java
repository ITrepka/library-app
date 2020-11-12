package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.repository.BookCopyRepository;
import com.itrepka.libraryapp.repository.BookRepository;
import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookCopyDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.mapper.BookCopyDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private BookCopyDtoMapper bookCopyDtoMapper;
    @Autowired
    private BookRepository bookRepository;

    public List<BookCopyDto> getAllBookCopies() {
        return bookCopyRepository.findAll().stream()
                .map(bookCopy -> bookCopyDtoMapper.toDto(bookCopy))
                .collect(Collectors.toList());
    }

    @Transactional
    public BookCopyDto getBookCopyById(long id) throws BookCopyNotFoundException {
        return bookCopyRepository.findById(id)
                .map(bookCopy -> bookCopyDtoMapper.toDto(bookCopy))
                .orElseThrow(() -> new BookCopyNotFoundException("Not found bookCopy with id = " + id));
    }

    @Transactional
    public BookCopyDto addNewBookCopy(CreateUpdateBookCopyDto createUpdateBookCopyDto) throws BookNotFoundException {
        BookCopy bookCopy = bookCopyDtoMapper.toModel(createUpdateBookCopyDto);
        BookCopy savedBookCopy = bookCopyRepository.save(bookCopy);
        return bookCopyDtoMapper.toDto(savedBookCopy);
    }

    @Transactional
    public BookCopyDto updateBookCopyById(long id, CreateUpdateBookCopyDto createUpdateBookCopyDto) throws BookCopyNotFoundException, BookNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new BookCopyNotFoundException("Not found bookCopy with id = " + id));

        Book book = bookRepository.findById(createUpdateBookCopyDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Not Found Book with id = " + createUpdateBookCopyDto.getBookId()));
        bookCopy.setBook(book);

        BookCopy savedBookCopy = bookCopyRepository.save(bookCopy);
        return bookCopyDtoMapper.toDto(savedBookCopy);
    }


    public BookCopyDto deleteBookCopyById(long id) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new BookCopyNotFoundException("Not found bookCopy with id = " + id));
        bookCopyRepository.deleteById(id);
        return bookCopyDtoMapper.toDto(bookCopy);
    }
}
