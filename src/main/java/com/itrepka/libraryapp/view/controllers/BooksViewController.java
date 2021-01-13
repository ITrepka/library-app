package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
