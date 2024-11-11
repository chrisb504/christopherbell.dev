package dev.christopherbell.thevoid.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  /**
   * Returns the home page.
   *
   * @return index/html
   */
  @GetMapping(value = "/")
  public String getHomePage(HttpServletRequest request) {
    return "index.html";
  }

  /**
   * Returns the login page.
   *
   * @return login/html
   */
  @GetMapping(value = "/login")
  public String getLoginPage(HttpServletRequest request) {
    return "login.html";
  }

  /**
   * Returns the create account page.
   *
   * @return login/html
   */
  @GetMapping(value = "/signup")
  public String getCreateAccountPage(HttpServletRequest request) {
    return "sign_up.html";
  }
}
