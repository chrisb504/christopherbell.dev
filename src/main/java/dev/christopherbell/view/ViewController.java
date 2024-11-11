package dev.christopherbell.view;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

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
    return "blog.html";
  }

  /**
   * Returns the blog page.
   *
   * @return html for photography.html home
   */
  @GetMapping(value = "/photography")
  public String getPhotographyPage() {
    return "photo/photography.html";
  }

  /**
   * Returns the photography.html usage page.
   *
   * @return html for photography.html usage page
   */
  @GetMapping(value = "/photography/usage")
  public String getPhotographyUsagePage() {
    return "photo/usage.html";
  }

  /**
   * Returns the whatsforlunch page.
   *
   * @return whatsforlunch home
   */
  @GetMapping(value = "/wfl")
  public String getWhatsForLunch() {
    return "whatsforlunch.html";
  }

  /**
   * Returns the home page.
   *
   * @return index/html
   */
  @GetMapping(value = "/void")
  public String getHome(HttpServletRequest request) {
    return "void/index.html";
  }

  /**
   * Returns the login page.
   *
   * @return login/html
   */
  @GetMapping(value = "/void/login")
  public String getLoginPage(HttpServletRequest request) {
    return "void/login.html";
  }

  /**
   * Returns the create account page.
   *
   * @return login/html
   */
  @GetMapping(value = "/void/signup")
  public String getCreateAccountPage(HttpServletRequest request) {
    return "void/sign_up.html";
  }

}
