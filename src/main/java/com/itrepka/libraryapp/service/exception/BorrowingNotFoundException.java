package com.itrepka.libraryapp.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BorrowingNotFoundException extends Exception {
    public BorrowingNotFoundException (String message) {
        super(message);
    }
}
