package com.azurras.website.controllers;

import com.azurras.website.services.FlushService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FlushController {
    private final Log LOG = LogFactory.getLog(FlushController.class);
    private FlushService flushService;

    @Autowired
    public FlushController(FlushService flushService) {
        this.flushService = flushService;
    }

    /**
     * Returns the flush page.
     * @return flush
     */
    @RequestMapping(value = "/flush", method = RequestMethod.GET)
    public String getFlush() {
        return "flush";
    }
}