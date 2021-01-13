package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookDto;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.dtos.CreateBookFormDto;
import com.itrepka.libraryapp.view.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BooksViewController {
    @Autowired
    private ViewService viewService;

    @GetMapping("books")
    public ModelAndView displayBooksTable() throws AuthorNotFoundException, BookCopyNotFoundException {
        ModelAndView mv = new ModelAndView("books");
        List<BookViewDto> books = viewService.getBooksToDisplay();
        mv.addObject("books", books);
        return mv;
    }

    @GetMapping("books/add-new")
    public ModelAndView displayFormToAddBook() {
        ModelAndView mv = new ModelAndView("add-book");
        CreateBookFormDto createBookDto = new CreateBookFormDto();
        mv.addObject("book", createBookDto);
        return mv;
    }

    @PostMapping("books/add-new")
    public ModelAndView addBookToDb(@ModelAttribute(name = "book") CreateBookFormDto createBookFormDto) throws BookAlreadyExistException, BookNotFoundException, AuthorNotFoundException, AuthorAlreadyExistException {
        List<BookCopyDto> bookCopyDtoList = viewService.addBookToDbAndCreateCopies(createBookFormDto);
        ModelAndView mv = new ModelAndView("redirect:/books");
        return mv;
    }
}
