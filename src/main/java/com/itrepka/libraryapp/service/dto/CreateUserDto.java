package com.itrepka.libraryapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDto {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
}
