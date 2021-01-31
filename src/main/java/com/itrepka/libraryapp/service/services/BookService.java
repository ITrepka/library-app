package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.repository.BookCopyRepository;
import com.itrepka.libraryapp.repository.BookRepository;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookDto;
import com.itrepka.libraryapp.service.exception.BookAlreadyExistException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.mapper.BookDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper;
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> bookDtoMapper.toDto(book))
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDto getBookById(long id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .map(book -> bookDtoMapper.toDto(book))
                .orElseThrow(() -> new BookNotFoundException("Not found book with id = " + id));
    }

    @Transactional
    public BookDto addNewBook(CreateUpdateBookDto createUpdateBookDto) throws BookAlreadyExistException {
        boolean isExist = getAllBooks().stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(createUpdateBookDto.getTitle()) && b.getPublishedDate().isEqual(createUpdateBookDto.getPublishedDate()));


        if (isExist) {
            throw new BookAlreadyExistException("Book already exist");
        }

        Book book = bookDtoMapper.toModel(createUpdateBookDto);
        Book savedBook = bookRepository.save(book);
        return bookDtoMapper.toDto(savedBook);
    }

    @Transactional
    public BookDto updateBookById(long id, CreateUpdateBookDto createUpdateBookDto) throws BookNotFoundException, BookAlreadyExistException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Not found book with id = " + id));

        boolean isExist = getAllBooks().stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(createUpdateBookDto.getTitle()) && b.getPublishedDate().isEqual(createUpdateBookDto.getPublishedDate()));


        if (isExist) {
            throw new BookAlreadyExistException("Book already exist");
        }

        book.setTitle(createUpdateBookDto.getTitle());
        book.setLongDescription(createUpdateBookDto.getLongDescription());
        book.setShortDescription(createUpdateBookDto.getShortDescription());
        book.setPageCount(createUpdateBookDto.getPageCount());
        book.setPublishedDate(createUpdateBookDto.getPublishedDate());
        book.setThumbnailUrl(createUpdateBookDto.getThumbnailUrl());

        Book savedBook = bookRepository.save(book);
        return bookDtoMapper.toDto(savedBook);
    }


    public BookDto deleteBookById(long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Not found book with id = " + id));


        List<BookCopy> bookCopies = book.getBookCopies();
        for (BookCopy bookCopy : bookCopies) {
            bookCopyRepository.delete(bookCopy);
        }
        bookRepository.deleteById(id);
        return bookDtoMapper.toDto(book);
    }
}
