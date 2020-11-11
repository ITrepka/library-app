package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.AuthorDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateAuthorDto;
import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable long id) throws AuthorNotFoundException {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorDto addNewAuthor(@RequestBody CreateUpdateAuthorDto createUpdateAuthorDto) {
        return authorService.addNewAuthor(createUpdateAuthorDto);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthorById(@PathVariable long id, @RequestBody CreateUpdateAuthorDto createUpdateAuthorDto) throws AuthorNotFoundException {
        return authorService.updateAuthorById(id, createUpdateAuthorDto);
    }

    @DeleteMapping("/{id}")
    public AuthorDto deleteAuthorById(@PathVariable long id) throws AuthorNotFoundException {
        return authorService.deleteAuthorById(id);
    }
}
