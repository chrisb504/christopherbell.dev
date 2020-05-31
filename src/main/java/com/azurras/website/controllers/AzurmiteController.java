package com.azurras.website.controllers;

import com.azurras.website.services.AzurmiteService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AzurmiteController {
    //private final Logger LOG = LogManager.getLogger(AzurmiteController.class);
    private AzurmiteService azurmiteService;

    @Autowired
    public AzurmiteController(AzurmiteService azurmiteService) {
        this.azurmiteService = azurmiteService;
    }
}