package com.itrepka.libraryapp.service.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUpdateAuthorDto {
    private String fullName;
    private String nickname;
    private Integer birthYear;
    private Integer deathYear;
    private String birthplace;
}
