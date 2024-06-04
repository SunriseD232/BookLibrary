package com.example.userlib.Controller;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.ROLE;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Services.BookGiveAwayService;
import com.example.userlib.Services.BookingService;
import com.example.userlib.Services.UserServiceImpl;
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
    List<BookGivenAway> booksGivenAway = bookGiveAwayService.findGiveAwayByUser(user);
    model.addAttribute("booksGivenAway", booksGivenAway);
    model.addAttribute("bookings", bookings);
    return "profile";
  }

  @GetMapping("/userprofile")
  public String showUserProfile(@AuthenticationPrincipal UserDetails userDetails,
      @RequestParam(required = false) String username, Model model) {
    if (userService.findUserByUsername(userDetails.getUsername()).getRole() == ROLE.ADMIN) {
      try {
        UserImpl user = userService.findUserByUsername(username);
        List<Booking> bookings = bookingService.findBookingsByUser(user);
        List<BookGivenAway> booksGivenAway = bookGiveAwayService.findGiveAwayByUser(user);
        model.addAttribute("isBlocked", user.getIsBlocked());
        model.addAttribute("bookings", bookings);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("booksGivenAway", booksGivenAway);
        return "userProfile";
      } catch (Exception e) {
        System.out.println(e);
        return "/admin";
      }
    } else {
      return "/admin";
    }
  }

  @PostMapping("/pay-fine")
  public String payFine(@RequestParam String username) {
    UserImpl user = userService.findUserByUsername(username);
    user.setIsBlocked(false);
    user.setStrike(0);
    userService.saveUser(user);
    return "redirect:/userprofile?username=" + username;
  }
}
