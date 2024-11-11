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
  public String getWhatsForLunchPage() {
    return "whatsforlunch.html";
  }

  /**
   * Returns the void's home page.
   *
   * @return index/html
   */
  @GetMapping(value = "/void")
  public String getVoidHomePage(HttpServletRequest request) {
    return "void/index.html";
  }

  /**
   * Returns the void's login page.
   *
   * @return login/html
   */
  @GetMapping(value = "/void/login")
  public String getVoidLoginPage(HttpServletRequest request) {
    return "void/login.html";
  }

  /**
   * Returns the void's create account page.
   *
   * @return sign_up/html
   */
  @GetMapping(value = "/void/signup")
  public String getVoidCreateAccountPage(HttpServletRequest request) {
    return "void/sign_up.html";
  }

  /**
   * Returns The Bell's home page.
   *
   * @return index/html
   */
  @GetMapping(value = "/thebell")
  public String getTheBellHomePage(HttpServletRequest request) {
    return "thebell/index.html";
  }

  /**
   * Returns The Bell's Tony page.
   *
   * @return tony/html
   */
  @GetMapping(value = "/thebell/tony")
  public String getTheBellTonyPage(HttpServletRequest request) {
    return "thebell/tony.html";
  }

}
