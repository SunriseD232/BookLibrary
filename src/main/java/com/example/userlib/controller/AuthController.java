package com.example.userlib.controller;

import com.example.userlib.services.User.UserImpl;
import com.example.userlib.services.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {
  @Autowired
  private UserServiceImpl userService;

  @GetMapping("/register")
  public String showRegisterForm(){
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute UserImpl user){
    userService.saveUser(user);
    return  "redirect:/login";
  }

  @GetMapping("/login")
  public String showLoginForm(){
    return "login";
  }

  @GetMapping("/FAQ")
  public String showFAQForm(){
    return "FAQ";
  }


}
