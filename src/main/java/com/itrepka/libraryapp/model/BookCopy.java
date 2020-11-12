package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "bookCopyId")
@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookCopyId;
    @ManyToOne
    private Book book;
    @ManyToMany
    private List<Borrowing> borrowings;
}
