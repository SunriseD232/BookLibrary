package com.example.userlib.Controller;

import com.example.userlib.implementation.Booking.Booking;
import com.example.userlib.implementation.GiveAway.BookGivenAwayImpl;
import com.example.userlib.implementation.User.ROLE;
import com.example.userlib.implementation.User.UserImpl;
import com.example.userlib.services.BookGiveAwayService;
import com.example.userlib.services.BookingService;
import com.example.userlib.services.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

  @Autowired
  private BookingService bookingService;

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private BookGiveAwayService bookGiveAwayService;

  @GetMapping("/profile")
  public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {

    UserImpl user = userService.findUserByUsername(userDetails.getUsername());
    List<Booking> bookings = bookingService.findBookingsByUser(user);
    List<BookGivenAwayImpl> booksGivenAway = bookGiveAwayService.findGiveAwayByUser(user);
    model.addAttribute("booksGivenAway", booksGivenAway);
    model.addAttribute("bookings", bookings);
    return "profile";
  }

  @GetMapping("/userprofile")
  public String showUserProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false) String username, Model model){
    if(userService.findUserByUsername(userDetails.getUsername()).getRole() == ROLE.ADMIN){
      try {
        UserImpl user = userService.findUserByUsername(username);
        List<Booking> bookings = bookingService.findBookingsByUser(user);
        List<BookGivenAwayImpl> booksGivenAway = bookGiveAwayService.findGiveAwayByUser(user);
        model.addAttribute("bookings", bookings);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("booksGivenAway", booksGivenAway);
        return "userProfile";
      }
      catch (Exception e){
        return "/admin";
      }
    }
    else{
      return "/admin";
    }
  }

}
