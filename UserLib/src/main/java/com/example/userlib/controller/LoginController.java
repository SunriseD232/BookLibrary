package com.example.userlib.controller;

import com.example.userlib.services.user.UserImpl;
import com.example.userlib.services.user.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String processLogin(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      Model model,
      HttpSession session
  ) {
    UserImpl user = userService.authenticateUser(username, password);
    if (user != null) {
      // Успешная аутентификация
      session.setAttribute("user", user);
      return "redirect:/";
    } else {
      // Неудачная аутентификация
      model.addAttribute("error", "Invalid username or password");
      return "login";
    }
  }
}