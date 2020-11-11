package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private List<Author> authors;
    private String shortDescription;
    private String longDescription;
    private String thumbnailUrl;
    private List<Category> categories;
    private List<BookCopy> bookCopies;
}
