package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateCategoryBookDto;
import com.itrepka.libraryapp.service.exception.CategoryNotFoundException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.services.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books-categories")
public class BookCategoryController {
    @Autowired
    private CategoryBookService categoryBookService;

    @GetMapping("/{categoryId}")
    public List<BookDto> getCategoryBooks(@PathVariable Integer categoryId) throws CategoryNotFoundException {
        return categoryBookService.getCategoryBooksByCategoryId(categoryId);
    }

    @PostMapping
    public BookDto addCategoryToBook(@RequestBody CreateCategoryBookDto createUpdateCategoryBookDto) throws CategoryNotFoundException, BookNotFoundException {
        return categoryBookService.addCategoryToBook(createUpdateCategoryBookDto);
    }

    @DeleteMapping("/{categoryId}/{bookId}")
    public BookDto deleteCategoryFromBook(@PathVariable Long bookId, @PathVariable Integer categoryId) throws CategoryNotFoundException, BookNotFoundException {
        return categoryBookService.deleteCategoryFromBook(bookId, categoryId);
    }
}
