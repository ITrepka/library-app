package com.itrepka.libraryapp.service.dto;

import com.itrepka.libraryapp.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {
    private Long authorId;
    private String name;
    private String surname;
    private Integer birthYear;
    private Integer deathYear;
    private String birthplace;
    private String nickname;
    private List<Long> createdBooksIds;
}
