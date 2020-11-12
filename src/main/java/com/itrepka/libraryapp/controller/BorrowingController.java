package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.BorrowingDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBorrowingDto;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.service.exception.BorrowingNotFoundException;
import com.itrepka.libraryapp.service.exception.UserNotFoundException;
import com.itrepka.libraryapp.service.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    public List<BorrowingDto> getAllBorrowings() {
        return borrowingService.getAllBorrowings();
    }

    @GetMapping("/{id}")
    public BorrowingDto getBorrowingById(@PathVariable long id) throws BorrowingNotFoundException {
        return borrowingService.getBorrowingById(id);
    }

    @PostMapping
    public BorrowingDto addNewBorrowing(@RequestBody CreateUpdateBorrowingDto createUpdateBorrowingDto) throws BookCopyNotFoundException, UserNotFoundException {
        return borrowingService.addNewBorrowing(createUpdateBorrowingDto);
    }

    @PutMapping("/{id}")
    public BorrowingDto updateBorrowingById(@PathVariable long id, @RequestBody CreateUpdateBorrowingDto createUpdateBorrowingDto) throws BorrowingNotFoundException, BookCopyNotFoundException, UserNotFoundException {
        return borrowingService.updateBorrowingById(id, createUpdateBorrowingDto);
    }

    @DeleteMapping("/{id}")
    public BorrowingDto deleteBorrowingById(@PathVariable long id) throws BorrowingNotFoundException {
        return borrowingService.deleteBorrowingById(id);
    }
}
