package com.itrepka.libraryapp.model;

import java.time.OffsetDateTime;

public class Borrowing {
    private Long borrowingId;
    private Reader borrowingReader;
    private BookCopy bookCopy;
    private OffsetDateTime borrowingBookDate;
    private OffsetDateTime returningBookDate;
    private OffsetDateTime finalReturningBookDate;
}
