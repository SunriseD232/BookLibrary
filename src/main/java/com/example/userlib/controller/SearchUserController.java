package com.example.userlib.Controller;

import com.example.userlib.implementation.User.ROLE;
import com.example.userlib.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchUserController {

  @Autowired
  private UserServiceImpl userService;

  @GetMapping("/admin")
  public String showAdminSearch(@AuthenticationPrincipal UserDetails userDetails){
    if (userService.findUserByUsername(userDetails.getUsername()).getRole() == ROLE.ADMIN){
      return "admin";
    }
    return "/FAQ";
  }


}
