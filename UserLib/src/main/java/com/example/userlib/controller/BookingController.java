package com.example.userlib.controller;

import com.example.userlib.services.Booking.Booking;
import com.example.userlib.services.Booking.BookingService;
import com.example.userlib.services.book.Book;
import com.example.userlib.services.book.BookService;
import com.example.userlib.services.user.UserImpl;
import com.example.userlib.services.user.UserService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {
  @Autowired
  private BookingService bookingService;

  @Autowired
  private UserService userService;

  @Autowired
  private BookService bookService;

  @GetMapping("/booking")
  public String showBookingPage(@RequestParam Long userId, Model model) {
    System.out.println(userId);
    UserImpl user = userService.findById(userId);
    List<Booking> bookings = bookingService.getAllBookings();

    model.addAttribute("bookings", bookings);
    return "booking";
  }

  @PostMapping("/add-to-booking")
  public String addToBooking(@RequestParam Long userId, @RequestParam Long bookId) {
    UserImpl user = userService.findById(userId);
    Book book = bookService.findById(bookId);

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setBooks(Collections.singletonList(book));

    bookingService.saveBooking(booking);

    return "redirect:/booking";
  }
}
