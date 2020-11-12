package com.itrepka.libraryapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryBookDto {
    private Integer categoryId;
    private Long bookId;
}