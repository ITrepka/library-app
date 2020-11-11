package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookDto;
import com.itrepka.libraryapp.service.exception.BookAlreadyExistException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable long id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDto addNewBook(@RequestBody CreateUpdateBookDto createUpdateBookDto) throws BookAlreadyExistException {
        return bookService.addNewBook(createUpdateBookDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBookById(@PathVariable long id, @RequestBody CreateUpdateBookDto createUpdateBookDto) throws BookNotFoundException, BookAlreadyExistException {
        return bookService.updateBookById(id, createUpdateBookDto);
    }

    @DeleteMapping("/{id}")
    public BookDto deleteBookById(@PathVariable long id) throws BookNotFoundException {
        return bookService.deleteBookById(id);
    }
}
