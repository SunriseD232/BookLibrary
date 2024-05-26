package com.example.userlib.Controller;

import com.example.userlib.implementation.book.Book;
import com.example.userlib.services.BookService;
import com.example.userlib.services.UserServiceImpl;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private UserServiceImpl userService;


  @PostMapping("/books")
  public String searchBooks(@AuthenticationPrincipal UserDetails userDetails,
      @RequestParam(required = false) String keyword, Model model) {
    if (userService.findUserByUsername(userDetails.getUsername()).getIsBlocked() == Boolean.FALSE) {
      List<Book> books;
      if (keyword == null || keyword.isEmpty()) {
        books = bookService.findAll();
      } else {
        books = bookService.findByTitle(keyword);
      }
      books = books.stream()
          .sorted(Comparator.comparing(Book::getTitle))
          .collect(Collectors.toList());

      model.addAttribute("books", books);
      model.addAttribute("username", userDetails.getUsername());
      return "books";
    } else {
      return "block";
    }
  }

  @PostMapping("/return-book")
  public String returnBook(@RequestParam String username, @RequestParam Long bookGivenAwayId) {
    bookService.returnBook(username, bookGivenAwayId);
    return "redirect:/userprofile?username=" + username;
  }
}
