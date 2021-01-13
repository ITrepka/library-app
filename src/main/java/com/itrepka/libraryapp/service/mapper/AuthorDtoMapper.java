package com.itrepka.libraryapp.service.mapper;

import com.itrepka.libraryapp.model.Author;
import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.service.dto.AuthorDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateAuthorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorDtoMapper {
    public AuthorDto toDto(Author author) {
        List<Long> booksIds = author.getCreatedBooks().stream().map(Book::getBookId).collect(Collectors.toList());
        return AuthorDto.builder()
                .authorId(author.getAuthorId())
                .fullName(author.getFullName())
                .nickname(author.getNickname())
                .birthplace(author.getBirthplace())
                .birthYear(author.getBirthYear())
                .deathYear(author.getDeathYear())
                .createdBooksIds(booksIds)
                .build();
    }

    public Author toModel(CreateUpdateAuthorDto createUpdateAuthorDto) {
        return Author.builder()
                .authorId(null)
                .fullName(createUpdateAuthorDto.getFullName())
                .nickname(createUpdateAuthorDto.getNickname())
                .birthplace(createUpdateAuthorDto.getBirthplace())
                .birthYear(createUpdateAuthorDto.getBirthYear())
                .deathYear(createUpdateAuthorDto.getDeathYear())
                .createdBooks(new ArrayList<>())
                .build();
    }
}
