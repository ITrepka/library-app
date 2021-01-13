package com.itrepka.libraryapp.view.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBookFormDto {
    private String title;
    private Integer pageCount;
    private String publishedDate;
    private String authors;
    private Integer copiesNumber;
}
