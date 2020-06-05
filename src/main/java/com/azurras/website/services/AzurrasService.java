package com.azurras.website.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzurrasService {
    //private final Logger LOG = LogManager.getLogger(AzurrasService.class);
    private BlogService blogService;
    private WhatsForLunchService whatsForLunchService;

    @Autowired
    public AzurrasService(BlogService blogService, WhatsForLunchService whatsForLunchService) {
        this.blogService = blogService;
        this.whatsForLunchService = whatsForLunchService;
    }
}