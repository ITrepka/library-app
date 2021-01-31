package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.service.services.BorrowingService;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.dtos.BorrowingViewDto;
import com.itrepka.libraryapp.view.dtos.CreateBookFormDto;
import com.itrepka.libraryapp.view.dtos.UpdateBookFormDto;
import com.itrepka.libraryapp.view.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BorrowingsViewController {
    @Autowired
    private ViewService viewService;
    @Autowired
    private BorrowingService borrowingService;


    @GetMapping("borrowings")
    public ModelAndView displayBooksTable(@RequestParam(required = false) String s) throws AuthorNotFoundException, BookCopyNotFoundException, BookNotFoundException, UserNotFoundException {
        ModelAndView mv = new ModelAndView("borrowings");
        List<BorrowingViewDto> borrowings = viewService.getBorrowingsToDisplay();
        if (s != null) {
            borrowings = borrowings.stream()
                    .filter(borrowing -> borrowing.getTitle().toLowerCase().contains(s.toLowerCase())
                            || borrowing.getReader().toLowerCase().contains(s.toLowerCase()))
                    .collect(Collectors.toList());
        }
        mv.addObject("borrowings", borrowings);
        return mv;
    }

//    @GetMapping("books/add-new")
//    public ModelAndView displayFormToAddBook() {
//        ModelAndView mv = new ModelAndView("add-book");
//        CreateBookFormDto createBookDto = new CreateBookFormDto();
//        mv.addObject("book", createBookDto);
//        return mv;
//    }
//
//    @PostMapping("books/add-new")
//    public ModelAndView addBookToDb(@ModelAttribute(name = "book") CreateBookFormDto createBookFormDto) throws BookAlreadyExistException, BookNotFoundException, AuthorNotFoundException, AuthorAlreadyExistException {
//        List<BookCopyDto> bookCopyDtoList = viewService.addBookToDbAndCreateCopies(createBookFormDto);
//        ModelAndView mv = new ModelAndView("redirect:/books");
//        return mv;
//    }
//
//    @GetMapping("books/remove/{id}")
//    public ModelAndView removeBook(@PathVariable Long id) throws BookNotFoundException {
//        BookDto bookDto = bookService.deleteBookById(id);
//        ModelAndView mv = new ModelAndView("redirect:/books");
//        return mv;
//    }
//
//    @GetMapping("books/edit/{id}")
//    public ModelAndView displayBookEditForm(@PathVariable Long id) throws BookNotFoundException, AuthorNotFoundException {
//        UpdateBookFormDto updateBookFormDto = viewService.getPreparedUpdateBookFormDto(id);
//        ModelAndView mv = new ModelAndView("edit-book");
//        mv.addObject("book", updateBookFormDto);
//        return mv;
//    }
//
//    @PostMapping("books/edit")
//    public ModelAndView updateBook(@ModelAttribute(name = "book") UpdateBookFormDto updateBookFormDto) throws AuthorAlreadyExistException, BookNotFoundException, AuthorNotFoundException {
//        viewService.updateBook(updateBookFormDto);
//        ModelAndView mv = new ModelAndView("redirect:/books/info/" + updateBookFormDto.getBookId());
//        return mv;
//    }
}
