package com.itrepka.libraryapp.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCopyDto {
    private Long bookCopyId;
    private Long bookId;
    private List<Long> borrowingsIds;
    private Boolean isAvailableToBorrow;
}
