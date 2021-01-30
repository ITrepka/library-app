package com.itrepka.libraryapp.view.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "readerId")
public class ReaderViewDto {
    private Long readerId;
    private String name;
    private String surname;
    private String email;
    private String address;
    private Double penalty;
}
