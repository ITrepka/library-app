package com.itrepka.libraryapp.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyExistException extends Exception{
    public BookAlreadyExistException(String message) {
        super(message);
    }
}
