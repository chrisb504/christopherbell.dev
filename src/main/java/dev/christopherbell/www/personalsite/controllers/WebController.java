package dev.christopherbell.www.personalsite.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    /**
     * Returns the home page.
     *
     * @return index/home
     */
    @GetMapping(value = "/")
    public String getHomePage(HttpServletRequest request) {
        return "index.html";
    }

    /**
     * Returns the blog page.
     *
     * @return blog home
     */
    @GetMapping(value = "/blog")
    public String getBlogPage(HttpServletRequest request) {
        return "blog/blog.html";
    }

    /**
     * Returns the blog page.
     *
     * @return html for photography.html home
     */
    @GetMapping(value = "/photography")
    public String getPhotographyPage(HttpServletRequest request) {
        return "photography/photography.html";
    }

    /**
     * Returns the photography.html usage page.
     *
     * @return html for photography.html usage page
     */
    @GetMapping(value = "/photography/usage")
    public String getPhotographyUsagePage(HttpServletRequest request) {
        return "photography/usage.html";
    }

    /**
     * Returns the resume page.
     *
     * @return resume
     */
    @GetMapping(value = "/resume")
    public String getResume(HttpServletRequest request) {
        return "resume";
    }

    /**
     * Returns the smp page.
     *
     * @return smp home
     */
    @GetMapping(value = "/smp")
    public String getSMP(HttpServletRequest request) {
        return "smp/smp.html";
    }

    /**
     * Returns the survive page.
     *
     * @return survive home
     */
    @GetMapping(value = "/survive")
    public String getSurviveHome(HttpServletRequest request) {
        return "survive/survive.html";
    }

    /**
     * Returns the whatsforlunch page.
     *
     * @return whatsforlunch home
     */
    @GetMapping(value = "/wfl")
    public String getWhatsForLunch(HttpServletRequest request) {
        return "wfl/whatsforlunch.html";
    }

    /**
     * Returns the dev page.
     *
     * @return dev
     */
    @GetMapping(value = "/dev")
    public String getTestHomePage(HttpServletRequest request) {
        return "dev";
    }
}
