package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.services.AzurrasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AzurrasServiceController {
    private final Logger LOG = LoggerFactory.getLogger(AzurrasServiceController.class);
    private final AzurrasService azurrasService;

    @Autowired
    public AzurrasServiceController(AzurrasService azurrasService) {
        this.azurrasService = azurrasService;
    }
}