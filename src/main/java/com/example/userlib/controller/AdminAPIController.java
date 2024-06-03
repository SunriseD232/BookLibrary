package com.example.userlib.Controller;

import com.example.userlib.Component.CustomUserScheduler;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Services.BookService;
import com.example.userlib.Services.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AdminAPIController {

  @Autowired
  private CustomUserScheduler customUserScheduler;

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private BookService bookService;

  @GetMapping("/users")
  @ResponseBody
  public List<UserImpl> getUsers(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails) == true) {
      return userService.findAllUsers();
    } else {
      return null;
    }
  }

  @GetMapping("/check-database")
  public String checkDatabase(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails) == true) {
      customUserScheduler.checkAndModifyDatabase();
    }
    return "Database checked and modified";
  }

  @GetMapping("/books")
  public List<Book> getAllBooks(@AuthenticationPrincipal UserDetails userDetails) {
    if (userService.isAdmin(userDetails) == true) {
      return bookService.findAll();
    } else {
      return null;
    }
  }
}