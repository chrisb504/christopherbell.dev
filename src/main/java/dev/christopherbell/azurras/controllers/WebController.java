package dev.christopherbell.azurras.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    /**
     * returns the test page
     *
     * @return test
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestPage() {
        return "test/test.html";
    }

    /**
     * Returns the home page.
     *
     * @return index/home
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }

    /**
     * Returns the blog page.
     *
     * @return blog home
     */
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String getBlogPage() {
        return "blog/blog.html";
    }

    /**
     * Returns the blog page.
     *
     * @return html for photography.html home
     */
    @RequestMapping(value = "/photography", method = RequestMethod.GET)
    public String getPhotographyPage() {
        return "photography/photography.html";
    }

    /**
     * Returns the photography.html usage page.
     *
     * @return html for photography.html usage page
     */
    @RequestMapping(value = "/photography/usage", method = RequestMethod.GET)
    public String getPhotographyUsagePage() {
        return "photography/usage.html";
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
     * @return smp home
     */
    @RequestMapping(value = "/smp", method = RequestMethod.GET)
    public String getSMP() {
        return "smp/smp.html";
    }

    /**
     * Returns the whatsforlunch page.
     *
     * @return whatsforlunch home
     */
    @RequestMapping(value = "/wfl", method = RequestMethod.GET)
    public String getWhatsForLunch() {
        return "wfl/whatsforlunch.html";
    }
}
