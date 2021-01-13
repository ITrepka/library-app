package com.itrepka.libraryapp.view.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "bookId")
public class BookViewDto {
    private Long bookId;
    private String title;
    private Integer pageCount;
    private LocalDate publishedDate;
    private List<String> authorsFullNames;
    private Integer copiesNumber;
    private Long currentlyBorrowed;
    private Long availableToBorrow;
}
