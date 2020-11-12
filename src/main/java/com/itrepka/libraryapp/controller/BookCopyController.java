package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookCopyDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-copies")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping
    public List<BookCopyDto> getAllBookCopies() {
        return bookCopyService.getAllBookCopies();
    }

    @GetMapping("/{id}")
    public BookCopyDto getBookCopyById(@PathVariable long id) throws BookCopyNotFoundException {
        return bookCopyService.getBookCopyById(id);
    }

    @PostMapping
    public BookCopyDto addNewBookCopy(@RequestBody CreateUpdateBookCopyDto createUpdateBookCopyDto) {
        return bookCopyService.addNewBookCopy(createUpdateBookCopyDto);
    }

    @PutMapping("/{id}")
    public BookCopyDto updateBookCopyById(@PathVariable long id, @RequestBody CreateUpdateBookCopyDto createUpdateBookCopyDto) throws BookCopyNotFoundException {
        return bookCopyService.updateBookCopyById(id, createUpdateBookCopyDto);
    }

    @DeleteMapping("/{id}")
    public BookCopyDto deleteBookCopyById(@PathVariable long id) throws BookCopyNotFoundException {
        return bookCopyService.deleteBookCopyById(id);
    }
}
