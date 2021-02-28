package dev.christopherbell.azurras.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzurrasService {
    private final Log LOG = LogFactory.getLog(AzurrasService.class);
    private WFLService wflService;

    @Autowired
    public AzurrasService(WFLService wflService) {
        this.wflService = wflService;
    }
}