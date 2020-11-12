package com.itrepka.libraryapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDto {
    private String password;
    private String name;
    private String surname;
    private String address;
    private Double penaltyForBooksNotReturnedOnTime;
}
