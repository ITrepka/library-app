package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBorrowingDto;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.service.services.BorrowingService;
import com.itrepka.libraryapp.view.dtos.*;
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
                    .sorted((b1, b2) -> b2.getBorrowingDate().compareTo(b1.getBorrowingDate()))
                    .collect(Collectors.toList());
        }
        mv.addObject("borrowings", borrowings);
        return mv;
    }

    @GetMapping("borrowings/add-new")
    public ModelAndView displayFormToAddBook() throws AuthorNotFoundException, BookCopyNotFoundException {
        ModelAndView mv = new ModelAndView("add-borrowing");

        List<BookViewDto> booksToDisplay = viewService.getBooksToDisplay();
        booksToDisplay = booksToDisplay.stream().filter(book -> book.getAvailableToBorrow() > 0)
                .collect(Collectors.toList());

        List<ReaderViewDto> readersToDisplay = viewService.getReadersToDisplay();
        readersToDisplay = readersToDisplay.stream().filter(reader -> reader.getPenalty() < 0.001).collect(Collectors.toList());
        CreateBorrowingFormDto createBorrowingDto = new CreateBorrowingFormDto();
        mv.addObject("readers", readersToDisplay);
        mv.addObject("borrowing", createBorrowingDto);
        mv.addObject("books", booksToDisplay);
        return mv;
    }
//
    @PostMapping("borrowings/add-new")
    public ModelAndView addBookToDb(@ModelAttribute(name = "borrowing") CreateBorrowingFormDto createBorrowingDto) throws BookAlreadyExistException, BookNotFoundException, AuthorNotFoundException, AuthorAlreadyExistException {
        viewService.addBorrowingToDb(createBorrowingDto);
        ModelAndView mv = new ModelAndView("redirect:/books");
        return mv;
    }
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
