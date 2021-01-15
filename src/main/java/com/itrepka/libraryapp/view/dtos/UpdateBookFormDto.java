package com.itrepka.libraryapp.view.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBookFormDto {
    private String title;
    private Integer pageCount;
    private String publishedDate;
    private String authors;
    private Integer newCopies;
}
