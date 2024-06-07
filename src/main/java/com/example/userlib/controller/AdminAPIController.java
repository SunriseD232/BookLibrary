package com.example.userlib.Controller;

import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Services.BookService;
import com.example.userlib.Services.BookingService;
import com.example.userlib.Services.UserServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminAPIController {

  private final UserServiceImpl userService;

  private final BookService bookService;

  private final BookingService bookingService;


  @GetMapping("/users")
  @ResponseBody
  public List<UserImpl> getUsers(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails)) {
      return userService.findAllUsers();
    } else {
      return null;
    }
  }

  @GetMapping("/check-database")
  public String checkDatabase(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails)) {
      userService.strikeUser();
    }
    return "Database checked and modified";
  }

  @GetMapping("/books")
  public List<Book> getAllBooks(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails)) {
      return bookService.findAll();
    } else {
      return null;
    }
  }

  @GetMapping("/deleteExpired")
  public String deleteExpiredBooking(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails)) {
      bookingService.deleteExpiredBooking();

    }
    return "Expired bookings deleted";
  }
}