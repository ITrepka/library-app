package com.itrepka.libraryapp.view.dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "borrowingId")
public class BorrowingViewDto {
    private Long borrowingId;
    private Long bookCopyId;
    private String title;
    private String reader;
    private LocalDate borrowingDate;
    private LocalDate finalReturnDate;
    private LocalDate returnDate;
    private Long bookId;
    private Long readerId;
}
