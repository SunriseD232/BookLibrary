package com.example.userlib.Controller;

import com.example.userlib.services.BookingService;
import com.example.userlib.implementation.User.UserImpl;
import com.example.userlib.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

  @Autowired
  private BookingService bookingService;

  @Autowired
  private UserServiceImpl userService;

  @PostMapping("/add-to-booking")
  public String bookBook(@RequestParam String username, @RequestParam Long bookId) {

    UserImpl user = userService.findUserByUsername(username);
    if (user != null) {
      bookingService.bookBook(user, bookId);
    }
    return "redirect:/profile";
  }
}
