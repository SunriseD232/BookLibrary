package com.example.userlib.Controller;

import com.example.userlib.Impl.User.ROLE;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

  private final UserServiceImpl userService;


  @GetMapping("/register")
  public String showRegisterForm() {
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute UserImpl user) {
    user.setRole(ROLE.USER);

    userService.saveUserWithEcnoder(user);
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  @GetMapping("/FAQ")
  public String showFAQForm() {
    return "FAQ";
  }
}
