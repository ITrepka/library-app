package com.itrepka.libraryapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "borrowingId")
@Entity
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long borrowingId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User borrowingUser;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BookCopy bookCopy;
    private OffsetDateTime borrowingBookDate;
    private OffsetDateTime returningBookDate;
    private OffsetDateTime finalReturningBookDate;
}
