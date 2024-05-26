package com.example.userlib.Controller;

import com.example.userlib.services.BookGiveAwayService;
import com.example.userlib.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class booksGivenAwayContoller {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private BookGiveAwayService bookGiveAwayService;

  @PostMapping("/give-book")
  public String booksGivenAway(@RequestParam String username, @RequestParam Long bookingId) {

    bookGiveAwayService.giveAway(userService.findUserByUsername(username), bookingId);
    return "redirect:/userprofile?username=" + username;
  }


}
