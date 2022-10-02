package dev.christopherbell.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    /**
     * returns the test page
     *
     * @return test
     */
    @GetMapping(value = "/test")
    public String getTestPage() {
        return "test/test.html";
    }

    /**
     * Returns the home page.
     *
     * @return index/home
     */
    @GetMapping(value = "/")
    public String getHomePage() {
        return "index.html";
    }

    /**
     * Returns the blog page.
     *
     * @return blog home
     */
    @GetMapping(value = "/blog")
    public String getBlogPage() {
        return "blog/blog.html";
    }

    /**
     * Returns the blog page.
     *
     * @return html for photography.html home
     */
    @GetMapping(value = "/photography")
    public String getPhotographyPage() {
        return "photography/photography.html";
    }

    /**
     * Returns the photography.html usage page.
     *
     * @return html for photography.html usage page
     */
    @GetMapping(value = "/photography/usage")
    public String getPhotographyUsagePage() {
        return "photography/usage.html";
    }

    /**
     * Returns the resume page.
     *
     * @return resume
     */
    @GetMapping(value = "/resume")
    public String getResume() {
        return "resume";
    }

    /**
     * Returns the smp page.
     *
     * @return smp home
     */
    @GetMapping(value = "/smp")
    public String getSMP() {
        return "smp/smp.html";
    }

    @GetMapping(value = "/survive")
    public String getSurviveHome() {
        return "survive/survive.html";
    }

    /**
     * Returns the whatsforlunch page.
     *
     * @return whatsforlunch home
     */
    @GetMapping(value = "/wfl")
    public String getWhatsForLunch() {
        return "wfl/whatsforlunch.html";
    }

    /**
     * Returns the html for the WFL add page.
     *
     * @return html page
     */
    @GetMapping(value = "/wfl/add")
    public String getWhatsForLunchAddPage() {
        return "whatsforlunchAdd";
    }

    /**
     * Returns the html for the WFL delete page.
     *
     * @return html page
     */
    @GetMapping(value = "/wfl/delete")
    public String getWhatsForLunchDeletePage() {
        return "whatsforlunchDelete";
    }

    @GetMapping(value = "/dev")
    public String getTestHomePage() {
        return "dev";
    }
}
