package com.example.userlib.Controller;

import com.example.userlib.Services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SearchUserController {

  private final UserServiceImpl userService;


  @GetMapping("/admin")
  public String showAdminSearch(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails)) {
      return "admin";
    }
    return "/FAQ";
  }
}
