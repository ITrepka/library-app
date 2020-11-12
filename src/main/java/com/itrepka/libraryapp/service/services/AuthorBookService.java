package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Author;
import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.repository.AuthorRepository;
import com.itrepka.libraryapp.repository.BookRepository;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateAuthorBookDto;
import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.exception.BookNotFoundException;
import com.itrepka.libraryapp.service.mapper.BookDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorBookService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper;

    public List<BookDto> getAuthorBooksByAuthorId(Long authorId) throws AuthorNotFoundException {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found"));

        return author.getCreatedBooks() == null ? null : author.getCreatedBooks()
                .stream()
                .map(book -> bookDtoMapper.toDto(book)).collect(Collectors.toList());
    }

    public BookDto addAuthorToBook(CreateAuthorBookDto createAuthorBookDto) throws AuthorNotFoundException, BookNotFoundException {
        Long authorId = createAuthorBookDto.getAuthorId();
        Long bookId = createAuthorBookDto.getBookId();

        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException("Author not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));

        author.getCreatedBooks().add(book);
        book.getAuthors().add(author);

        Author savedAuthor = authorRepository.save(author);
        Book savedBook = bookRepository.save(book);

        return bookDtoMapper.toDto(savedBook);
    }

    public BookDto deleteAuthorFromBook(Long bookId, Long authorId) throws AuthorNotFoundException, BookNotFoundException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException("Author not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));

        author.getCreatedBooks().remove(book);
        book.getAuthors().remove(author);

        return bookDtoMapper.toDto(book);
    }
}
