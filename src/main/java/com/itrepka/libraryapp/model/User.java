package com.itrepka.libraryapp.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "userId")
public class User {
    private Long userId;
    private String email;
    private String password;
    private Double penaltyForBooksNotReturnedOnTime;
    private String personalData;
    private List<Borrowing> borrowings;
    private Role role;
}
