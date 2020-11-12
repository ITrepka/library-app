package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Category;
import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.repository.CategoryRepository;
import com.itrepka.libraryapp.repository.BookRepository;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateCategoryBookDto;
import com.itrepka.libraryapp.service.exception.CategoryNotFoundException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.mapper.BookDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryBookService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper;

    public List<BookDto> getCategoryBooksByCategoryId(Integer categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));

        return category.getBooks() == null ? null : category.getBooks()
                .stream()
                .map(book -> bookDtoMapper.toDto(book)).collect(Collectors.toList());
    }

    public BookDto addCategoryToBook(CreateCategoryBookDto createCategoryBookDto) throws CategoryNotFoundException, BookNotFoundException {
        Integer categoryId = createCategoryBookDto.getCategoryId();
        Long bookId = createCategoryBookDto.getBookId();

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));

        category.getBooks().add(book);
        book.getCategories().add(category);

        Category savedCategory = categoryRepository.save(category);
        Book savedBook = bookRepository.save(book);

        return bookDtoMapper.toDto(savedBook);
    }

    public BookDto deleteCategoryFromBook(Long bookId, Integer categoryId) throws CategoryNotFoundException, BookNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));

        category.getBooks().remove(book);
        book.getCategories().remove(category);

        return bookDtoMapper.toDto(book);
    }
}
