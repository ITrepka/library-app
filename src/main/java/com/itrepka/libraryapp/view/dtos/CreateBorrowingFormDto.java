package com.itrepka.libraryapp.view.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBorrowingFormDto {
    private Long readerId;
    private Long bookId;
}
