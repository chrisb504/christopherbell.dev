package com.azurras.website.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AzurrasController {

    // @RequestMapping("/")
    // @ResponseBody
    // public String index() {
    //     return "index";
    // }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/resume")
    public String resume() {
        return "resume";
    }
}