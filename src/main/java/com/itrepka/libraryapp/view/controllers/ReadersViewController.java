package com.itrepka.libraryapp.view.controllers;

import com.itrepka.libraryapp.service.dto.UserDto;
import com.itrepka.libraryapp.service.exception.*;
import com.itrepka.libraryapp.service.services.UserService;
import com.itrepka.libraryapp.view.dtos.CreateReaderFormDto;
import com.itrepka.libraryapp.view.dtos.ReaderViewDto;
import com.itrepka.libraryapp.view.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReadersViewController {
    @Autowired
    private ViewService viewService;
    @Autowired
    private UserService userService;

    @GetMapping("readers")
    public ModelAndView displayreadersTable(@RequestParam(required = false) String s) {
        ModelAndView mv = new ModelAndView("readers");
        List<ReaderViewDto> readers = viewService.getReadersToDisplay();
        if (s != null) {
            readers = readers.stream()
                    .filter(reader -> reader.getName().toLowerCase().contains(s.toLowerCase())
                            || reader.getSurname().toLowerCase().contains(s.toLowerCase()))
                    .collect(Collectors.toList());
        }
        mv.addObject("readers", readers);
        return mv;
    }

    @GetMapping("readers/add-new")
    public ModelAndView displayFormToAddreader() {
        ModelAndView mv = new ModelAndView("add-reader");
        CreateReaderFormDto createReaderDto = new CreateReaderFormDto();
        mv.addObject("reader", createReaderDto);
        return mv;
    }
//
    @PostMapping("readers/add-new")
    public ModelAndView addReaderToDb(@ModelAttribute(name = "reader") CreateReaderFormDto createReaderFormDto) throws UserAlreadyExistException {
        viewService.addReaderToDb(createReaderFormDto);
        ModelAndView mv = new ModelAndView("redirect:/readers");
        return mv;
    }
//
    @GetMapping("readers/remove/{id}")
    public ModelAndView removereader(@PathVariable Long id) throws UserNotFoundException {
        UserDto userDto = userService.deleteUserById(id);
        ModelAndView mv = new ModelAndView("redirect:/readers");
        return mv;
    }
//
//    @GetMapping("readers/edit/{id}")
//    public ModelAndView displayreaderEditForm(@PathVariable Long id) throws readerNotFoundException, AuthorNotFoundException {
//        UpdatereaderFormDto updatereaderFormDto = viewService.getPreparedUpdatereaderFormDto(id);
//        ModelAndView mv = new ModelAndView("edit-reader");
//        mv.addObject("reader", updatereaderFormDto);
//        return mv;
//    }
//
//    @PostMapping("readers/edit")
//    public ModelAndView updatereader(@ModelAttribute(name = "reader") UpdatereaderFormDto updatereaderFormDto) throws AuthorAlreadyExistException, readerNotFoundException, AuthorNotFoundException {
//        viewService.updatereader(updatereaderFormDto);
//        ModelAndView mv = new ModelAndView("redirect:/readers/info/" + updatereaderFormDto.getreaderId());
//        return mv;
//    }
    
}
