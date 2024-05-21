package com.example.userlib.controller;

import com.example.userlib.services.user.UserImpl;
import com.example.userlib.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

  @Autowired
  private UserService userService;

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new UserImpl());
    return "register";
  }

  @PostMapping("/register")
  public String processRegistration(
      @Valid @ModelAttribute("user") UserImpl user,
      BindingResult result,
      Model model
  ) {
    if (result.hasErrors()) {
      return "register";
    }

    if (userService.isUsernameExists(user.getUsername())) {
      model.addAttribute("usernameError", "Username already exists");
      return "register";
    }

    if (userService.isEmailExists(user.getEmail())) {
      model.addAttribute("emailError", "Email already exists");
      return "register";
    }

    userService.saveUser(user);
    return "redirect:/login";
  }
}