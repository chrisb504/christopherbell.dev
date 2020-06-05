package com.azurras.website.controllers;

import com.azurras.website.services.AzurrasService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Purpose of this controller is to return top level secondary pages for the
 * website. This includes pages like blog. If the blog page had any childern
 * pages than the blog controller should handle outputing those pages.
 */
@Controller
public class AzurrasController {
   // private Logger LOG = LogManager.getLogger(AzurrasController.class);
    private AzurrasService azurrasService;

    @Autowired
    public AzurrasController(AzurrasService azurrasService) {
        this.azurrasService = azurrasService;
    }

    @GetMapping("/")
    public String getHomepage() {
        //LOG.info("Returning index page");
        return "index";
    }

    @GetMapping("/flush")
    public String getFlush() {
        //LOG.info("Returning flush page");
        return "flush";
    }

    @GetMapping("/resume")
    public String getResume() {
        //LOG.info("Returning resume page");
        return "resume";
    }
}