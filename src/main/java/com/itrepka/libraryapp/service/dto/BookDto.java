package com.itrepka.libraryapp.service.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "bookId")
public class BookDto {
    private Long bookId;
    private String title;
    private Integer pageCount;
    private OffsetDateTime publishedDate;
    private List<Long> authorsIds;
    private String shortDescription;
    private String longDescription;
    private String thumbnailUrl;
    private List<Integer> categoriesIds;
    private List<Long> bookCopiesIds;
}
