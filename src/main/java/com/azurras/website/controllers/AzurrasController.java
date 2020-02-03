package com.azurras.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzurrasController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/resume")
    public String resume() {
        return "resume";
    }
}