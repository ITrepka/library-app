package com.itrepka.libraryapp.service.dto;

import com.itrepka.libraryapp.model.Borrowing;
import com.itrepka.libraryapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String email;
    private Double penaltyForBooksNotReturnedOnTime;
    private String name;
    private String surname;
    private String address;
    private List<Long> borrowingsIds;
    private String role;
}
