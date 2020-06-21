package com.azurras.website.controllers;

import com.azurras.website.services.AzurrasService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Purpose of this controller is to return top level secondary pages for the
 * website. This includes pages like blog. If the blog page had any childern
 * pages than the blog controller should handle outputing those pages.
 */
@Controller
public class AzurrasController {
    private final Log LOG = LogFactory.getLog(AzurrasController.class);
    private AzurrasService azurrasService;

    @Autowired
    public AzurrasController(AzurrasService azurrasService) {
        this.azurrasService = azurrasService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomepage() {
        LOG.info("test");
        return "index";
    }

    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public String getResume() {
        return "resume";
    }
}