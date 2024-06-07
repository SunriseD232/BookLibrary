package com.example.userlib.Controller;

import com.example.userlib.Impl.book.Book;
import com.example.userlib.Services.BookService;
import com.example.userlib.Services.BookViewService;
import com.example.userlib.Services.UserServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  private final UserServiceImpl userService;

  private final BookViewService bookViewService;

  @PostMapping("/books")
  public String searchBooks(@AuthenticationPrincipal UserDetails userDetails,
      @RequestParam(required = false) String keyword, Model model) {

    if (userService.findUserByUsername(userDetails.getUsername()).getIsBlocked() == Boolean.FALSE) {
      List<Book> books = bookViewService.getBook(keyword);

      model.addAttribute("books", books);
      model.addAttribute("username", userDetails.getUsername());
      return "books";
    } else {
      return "block";
    }
  }

  @PostMapping("/return-book")
  public String returnBook(@RequestParam String username, @RequestParam Long bookGivenAwayId) {
    bookService.returnBook(bookGivenAwayId);
    return "redirect:/userprofile?username=" + username;
  }
}
