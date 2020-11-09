package com.itrepka.libraryapp.model;

import java.util.List;

public class Reader {
    private Long readerId;
    private String email;
    private String password;
    private Double penaltyForBooksNotReturnedOnTime;
    private String personalData;
    private List<Borrowing> borrowings;
}
