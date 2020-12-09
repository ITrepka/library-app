package com.itrepka.libraryapp.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainViewController {
    @GetMapping({"", "/", "/index", "/index.html"})
    public ModelAndView displayMenu() {
        ModelAndView mv = new ModelAndView("menu");
        return mv;
    }

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }
}
