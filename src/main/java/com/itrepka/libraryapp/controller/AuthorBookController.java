package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateAuthorBookDto;
import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.services.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors-books")
public class AuthorBookController {
    @Autowired
    private AuthorBookService authorBookService;

    @GetMapping("/{authorId}")
    public List<BookDto> getAuthorBooks(@PathVariable Long authorId) throws AuthorNotFoundException {
        return authorBookService.getAuthorBooksByAuthorId(authorId);
    }

    @PostMapping
    public BookDto addAuthorToBook(@RequestBody CreateAuthorBookDto createUpdateAuthorBookDto) throws AuthorNotFoundException, BookNotFoundException {
        return authorBookService.addAuthorToBook(createUpdateAuthorBookDto);
    }

    @DeleteMapping("/{authorId}/{bookId}")
    public BookDto deleteAuthorFromBook(@PathVariable Long bookId, @PathVariable Long authorId) throws AuthorNotFoundException, BookNotFoundException {
        return authorBookService.deleteAuthorFromBook(bookId, authorId);
    }

}
