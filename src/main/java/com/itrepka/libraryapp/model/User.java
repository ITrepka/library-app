package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "userId")
@Entity
public class User {
    private Long userId;
    private String email;
    private String password;
    private Double penaltyForBooksNotReturnedOnTime;
    private String personalData;
    @OneToMany(mappedBy = "borrowingUser")
    private List<Borrowing> borrowings;
    private Role role;
}
