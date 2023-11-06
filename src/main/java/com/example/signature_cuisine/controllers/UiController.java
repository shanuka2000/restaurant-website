package com.example.signature_cuisine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {
    @GetMapping("/")
    public String getHome() {return  "home";}

    @GetMapping("/menu")
    public String getMenu() {return  "menu";}
}
