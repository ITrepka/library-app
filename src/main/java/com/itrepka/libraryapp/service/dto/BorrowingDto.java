package com.itrepka.libraryapp.service.dto;

import com.itrepka.libraryapp.model.BookCopy;
import com.itrepka.libraryapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowingDto {
    private Long borrowingId;
    private Long borrowingUserId;
    private Long bookCopyId;
    private OffsetDateTime borrowingBookDate;
    private OffsetDateTime returningBookDate;
    private OffsetDateTime finalReturningBookDate;
}
