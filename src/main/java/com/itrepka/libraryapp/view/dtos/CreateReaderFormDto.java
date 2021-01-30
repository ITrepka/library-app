package com.itrepka.libraryapp.view.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateReaderFormDto {
    private String name;
    private String surname;
    private String email;
    private String address;
}
