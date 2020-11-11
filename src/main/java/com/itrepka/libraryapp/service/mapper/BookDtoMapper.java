package com.itrepka.libraryapp.service.mapper;

import com.itrepka.libraryapp.model.Author;
import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.model.Category;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDtoMapper {
    public BookDto toDto(Book book) {
        List<Long> authorsIds = book.getAuthors().stream().map(Author::getAuthorId).collect(Collectors.toList());
        List<Long> bookCopiesIds = book.getBookCopies().stream().map(BookCopy::getBookCopyId).collect(Collectors.toList());
        List<Integer> categoriesIds = book.getCategories().stream().map(Category::getCategoryId).collect(Collectors.toList());
        return BookDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .shortDescription(book.getShortDescription())
                .longDescription(book.getLongDescription())
                .pageCount(book.getPageCount())
                .publishedDate(book.getPublishedDate())
                .thumbnailUrl(book.getThumbnailUrl())
                .authorsIds(authorsIds)
                .bookCopiesIds(bookCopiesIds)
                .categoriesIds(categoriesIds)
                .build();
    }

    public Book toModel(CreateUpdateBookDto createUpdateBookDto) {
        return Book.builder()
                .bookId(null)
                .authors(null)
                .bookCopies(null)
                .categories(null)
                .title(createUpdateBookDto.getTitle())
                .shortDescription(createUpdateBookDto.getTitle())
                .longDescription(createUpdateBookDto.getLongDescription())
                .pageCount(createUpdateBookDto.getPageCount())
                .publishedDate(createUpdateBookDto.getPublishedDate())
                .thumbnailUrl(createUpdateBookDto.getThumbnailUrl())
                .build();
    }
}
