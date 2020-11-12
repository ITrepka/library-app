package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "userId")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String email;
    private String password;
    private Double penaltyForBooksNotReturnedOnTime;
    private String name;
    private String surname;
    private String address;
    @OneToMany(mappedBy = "borrowingUser")
    private List<Borrowing> borrowings;
    private Role role;
}
