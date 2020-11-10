package com.itrepka.libraryapp.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "readerId")
public class Reader {
    private Long readerId;
    private String email;
    private String password;
    private Double penaltyForBooksNotReturnedOnTime;
    private String personalData;
    private List<Borrowing> borrowings;
}
