package com.azurras.website.controllers;

import com.azurras.website.services.WhatsForLunchService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WhatsForLunchController {
    //private final Logger LOG = LogManager.getLogger(WhatsForLunchController.class);
    private WhatsForLunchService whatsForLunchService;

    @Autowired
    public WhatsForLunchController(WhatsForLunchService whatsForLunchService) {
        this.whatsForLunchService = whatsForLunchService;
    }

    @GetMapping("/whatsforlunch")
    public String getWhatsForLunch() {
        //LOG.info("Returning whatsforlunch page");
        return "whatsforlunch";
    }
}