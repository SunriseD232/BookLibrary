package com.example.userlib.Controller;

import com.example.userlib.Services.BookingService;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  private final UserServiceImpl userService;


  @PostMapping("/add-to-booking")
  public String bookBook(@RequestParam String username, @RequestParam Long bookId) {

    UserImpl user = userService.findUserByUsername(username);
    if (user != null) {
      bookingService.bookBook(user, bookId);
    }
    return "redirect:/profile";
  }
}
