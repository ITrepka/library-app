package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "bookId")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String title;
    private Integer pageCount;
    private OffsetDateTime publishedDate;
    @ManyToMany
    private List<Author> authors;
    private String shortDescription;
    private String longDescription;
    private String thumbnailUrl;
    @ManyToMany
    private List<Category> categories;
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BookCopy> bookCopies;
}
