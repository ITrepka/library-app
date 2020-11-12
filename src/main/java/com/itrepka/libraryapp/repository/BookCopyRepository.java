package com.itrepka.libraryapp.repository;

import com.itrepka.libraryapp.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository <BookCopy, Long> {
}
