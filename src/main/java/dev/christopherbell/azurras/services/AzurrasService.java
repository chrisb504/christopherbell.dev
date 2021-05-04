package dev.christopherbell.azurras.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzurrasService {
    private final Logger LOG = LoggerFactory.getLogger(AzurrasService.class);
    private WFLService wflService;

    @Autowired
    public AzurrasService(WFLService wflService) {
        this.wflService = wflService;
    }
}