package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.exception.BookCopyNotFoundException;
import com.itrepka.libraryapp.view.dtos.BookViewDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersViewController {
    @GetMapping("users")
    public ModelAndView displayBooksTable(@RequestParam(required = false) String s) throws {
        ModelAndView mv = new ModelAndView("users");
        List<UserViewDto> users = viewService.getUsersToDisplay();
//        if (s != null) {
//            books = books.stream()
//                    .filter(book -> book.getTitle().toLowerCase().contains(s.toLowerCase())
//                            || book.getAuthorsFullNames().toString().toLowerCase().contains(s.toLowerCase()))
//                    .collect(Collectors.toList());
//        }
        mv.addObject("users", users);
        return mv;
    }
}
