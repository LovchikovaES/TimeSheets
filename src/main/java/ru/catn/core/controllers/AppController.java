package ru.catn.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping({"/"})
    public String showIndex() {
        return "index";
    }
}
