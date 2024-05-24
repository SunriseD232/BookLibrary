package com.example.userlib.controller;

import com.example.userlib.services.Booking.Booking;
import com.example.userlib.services.Booking.Service.BookingService;
import com.example.userlib.services.User.UserImpl;
import com.example.userlib.services.User.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/profile")
  public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model){
    System.out.println("getting user");
    UserImpl user = userService.findUserByUsername(userDetails.getUsername());
    System.out.println("Getting bookings");
    List<Booking> bookings = bookingService.findBookingsByUser(user);
    model.addAttribute("bookings", bookings);
    return "profile";
  }
}
