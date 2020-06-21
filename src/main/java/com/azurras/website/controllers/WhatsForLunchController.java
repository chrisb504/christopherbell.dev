package com.azurras.website.controllers;

import com.azurras.website.services.WhatsForLunchService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WhatsForLunchController {
    private final Log LOG = LogFactory.getLog(WhatsForLunchController.class);
    private final WhatsForLunchService whatsForLunchService;

    @Autowired
    public WhatsForLunchController(final WhatsForLunchService whatsForLunchService) {
        this.whatsForLunchService = whatsForLunchService;
    }

    /**
     * Returns the whatsforlunch page.
     * @return whatsforlunch
     */
    @RequestMapping(value = "/whatsforlunch", method = RequestMethod.GET)
    public String getWhatsForLunch() {
        return "whatsforlunch";
    }
}