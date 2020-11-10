package com.itrepka.libraryapp.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "authorId")
public class Author {
    private Long authorId;
    private String name;
    private String surname;
    private List<Book> createdBooks;
}
