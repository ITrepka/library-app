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
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Book book;
    @OneToMany(mappedBy = "bookCopy", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Borrowing> borrowings;
    private Boolean isAvailableToBorrow;
}
