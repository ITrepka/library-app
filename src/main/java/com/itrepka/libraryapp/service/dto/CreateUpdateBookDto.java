package com.itrepka.libraryapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUpdateBookDto {
    private String title;
    private Integer pageCount;
    private LocalDate publishedDate;
    private String shortDescription;
    private String longDescription;
    private String thumbnailUrl;
}
