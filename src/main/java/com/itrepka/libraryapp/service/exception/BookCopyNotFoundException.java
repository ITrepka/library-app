package com.itrepka.libraryapp.service.exception;

import com.itrepka.libraryapp.model.BookCopy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookCopyNotFoundException extends Exception{
    public BookCopyNotFoundException(String message) {
        super(message);
    }
}
