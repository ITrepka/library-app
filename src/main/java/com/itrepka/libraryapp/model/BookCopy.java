package com.itrepka.libraryapp.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "bookCopyId")
public class BookCopy {
    private Long bookCopyId;
    private Book book;
    private List<Borrowing> borrowings;
}
