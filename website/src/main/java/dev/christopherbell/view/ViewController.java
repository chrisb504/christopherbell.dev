package dev.christopherbell.view;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC controller serving static application views.
 */
@Controller
public class ViewController {

  /**
   * Serves the SPA index page for primary app routes.
   *
   * @return {@code index.html}
   */
  @RequestMapping(value = {"/", "/blog", "/photos", "/login", "/signup", "/wfl"})
  public String getHomePage() {
    return "index.html";
  }

  @RequestMapping(value = "/{path:^(?!api|static|.*\\.).*$}")
  public String redirect() {
    return "forward:/index.html";
  }

  /**
   * Serves the Void home page.
   *
   * @return {@code void/index.html}
   */
  @GetMapping(value = "/void")
  public String getVoidHomePage(HttpServletRequest request) {
    return "void/index.html";
  }

  /**
   * Serves the Void login page.
   *
   * @return {@code void/login.html}
   */
  @GetMapping(value = "/void/login")
  public String getVoidLoginPage(HttpServletRequest request) {
    return "void/login.html";
  }

  /**
   * Serves the Void signup page.
   *
   * @return {@code void/sign_up.html}
   */
  @GetMapping(value = "/void/signup")
  public String getVoidCreateAccountPage(HttpServletRequest request) {
    return "void/sign_up.html";
  }

  /**
   * Serves The Bell home page.
   *
   * @return {@code thebell/index.html}
   */
  @GetMapping(value = "/thebell")
  public String getTheBellHomePage(HttpServletRequest request) {
    return "thebell/index.html";
  }

  /**
   * Serves The Bell "Tony" page.
   *
   * @return {@code thebell/tony.html}
   */
  @GetMapping(value = "/thebell/tony")
  public String getTheBellTonyPage(HttpServletRequest request) {
    return "thebell/tony.html";
  }

}
