package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "authorId")
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorId;
    private String name;
    private String surname;
    private Integer birthYear;
    private Integer deathYear;
    private String birthplace;
    private String nickname;
    @ManyToMany
    private List<Book> createdBooks;
}
