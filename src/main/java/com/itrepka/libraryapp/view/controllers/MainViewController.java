package com.itrepka.libraryapp.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainViewController {
    @GetMapping({"", "/", "/index", "/index.html"})
    public ModelAndView displayHomepage() {
        ModelAndView mv = new ModelAndView("homepage");
        return mv;
    }

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }
}
