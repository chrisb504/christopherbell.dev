package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.models.AzurrasResponse;
import dev.christopherbell.azurras.services.AzurrasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AzurrasServiceController {
    private final Logger LOG = LoggerFactory.getLogger(AzurrasServiceController.class);
    private final AzurrasService azurrasService;

    @Autowired
    public AzurrasServiceController(AzurrasService azurrasService) {
        this.azurrasService = azurrasService;
    }

    @GetMapping(value = "/service/images", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AzurrasResponse> getImages() {
        LOG.info("New request for images");
        return new ResponseEntity<>(azurrasService.getPhotographyImageDetails(), HttpStatus.OK);
    }
}