package com.azurras.website.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzurrasService {
    private final Log LOG = LogFactory.getLog(AzurrasService.class);

    @Autowired
    public AzurrasService() {
    }
}