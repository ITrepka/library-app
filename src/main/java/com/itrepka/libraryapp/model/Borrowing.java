package com.itrepka.libraryapp.model;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "borrowingId")
public class Borrowing {
    private Long borrowingId;
    private User borrowingUser;
    private BookCopy bookCopy;
    private OffsetDateTime borrowingBookDate;
    private OffsetDateTime returningBookDate;
    private OffsetDateTime finalReturningBookDate;
}
