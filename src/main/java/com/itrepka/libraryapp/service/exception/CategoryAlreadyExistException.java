package com.itrepka.libraryapp.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistException extends Exception {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
