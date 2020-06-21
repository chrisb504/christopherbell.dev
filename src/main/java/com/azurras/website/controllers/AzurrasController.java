package com.azurras.website.controllers;

import com.azurras.website.services.AzurrasService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AzurrasController {
    private final Log LOG = LogFactory.getLog(AzurrasController.class);
    private AzurrasService azurrasService;

    @Autowired
    public AzurrasController(AzurrasService azurrasService) {
        this.azurrasService = azurrasService;
    }

    /**
     * Returns the home page.
     * 
     * @return index
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomepage() {
        return "index";
    }

    /**
     * Returns the resume page.
     * 
     * @return resume
     */
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public String getResume() {
        return "resume";
    }
}