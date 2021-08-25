package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.services.AzurrasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AzurrasServiceController {
    private final Logger LOG = LoggerFactory.getLogger(AzurrasServiceController.class);
    private final AzurrasService azurrasService;

    @Autowired
    public AzurrasServiceController(AzurrasService azurrasService) {
        this.azurrasService = azurrasService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestPage() {
        return "test/test.html";
    }

    /**
     * Returns the home page.
     * 
     * @return index
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }

    /**
     * Returns the blog page.
     * 
     * @return blog
     */
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String getBlogPage() {
        return "blog";
    }

    /**
     * Returns the resume page.
     * 
     * @return resume
     */
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public String getResume() {
        return "resume";
    }

    /**
     * Returns the smp page.
     * 
     * @return smp
     */
    @RequestMapping(value = "/smp", method = RequestMethod.GET)
    public String getSMP() {
        return "smp";
    }

    /**
     * Returns the whatsforlunch page.
     * 
     * @return whatsforlunch
     */
    @RequestMapping(value = "/wfl", method = RequestMethod.GET)
    public String getWhatsForLunch() {
        return "whatsforlunch";
    }
}