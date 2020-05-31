package com.azurras.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Purpose of this controller is to return top level secondary pages
 * for the website. This includes pages like blog. If the blog page had any childern
 * pages than the blog controller should handle outputing those pages.
 */
@Controller
public class AzurrasController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/azurmite")
    public String azurmite() {
        return "azurmite";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/flush")
    public String flush() {
        return "flush";
    }

    @GetMapping("/resume")
    public String resume() {
        return "resume";
    }

    @GetMapping("/whatsforlunch")
    public String whatsforlunch() {
        return "whatsforlunch";
    }
}