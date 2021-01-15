package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.dto.BookCopyDto;
import com.itrepka.libraryapp.service.dto.BookDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateBookDto;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.service.services.BookService;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import com.itrepka.libraryapp.view.dtos.CreateBookFormDto;
import com.itrepka.libraryapp.view.dtos.UpdateBookFormDto;
import com.itrepka.libraryapp.view.service.ViewService;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BooksViewController {
    @Autowired
    private ViewService viewService;
    @Autowired
    private BookService bookService;


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

    @GetMapping("books/remove/{id}")
    public ModelAndView removeBook(@PathVariable Long id) throws BookNotFoundException {
        BookDto bookDto = bookService.deleteBookById(id);
        ModelAndView mv = new ModelAndView("redirect:/books");
        return mv;
    }

    @GetMapping("books/edit/{id}")
    public ModelAndView displayBookEditForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("edit-book");
        UpdateBookFormDto updateBookFormDto = new UpdateBookFormDto();
        mv.addObject("book", updateBookFormDto);
        return mv;
    }
}
