package com.example.userlib.Controller;

import com.example.userlib.Services.BookGiveAwayService;
import com.example.userlib.Services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class booksGivenAwayContoller {

  private final UserServiceImpl userService;

  private final BookGiveAwayService bookGiveAwayService;


  @PostMapping("/give-book")
  public String booksGivenAway(@RequestParam String username, @RequestParam Long bookingId) {

    bookGiveAwayService.giveAway(userService.findUserByUsername(username), bookingId);
    return "redirect:/userprofile?username=" + username;
  }
}
