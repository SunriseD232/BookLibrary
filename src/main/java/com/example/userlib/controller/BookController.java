package com.example.userlib.controller;

import com.example.userlib.services.book.Book;
import com.example.userlib.services.book.BookService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
  @Autowired
  private BookService bookService;

  @PostMapping("/books")
  public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
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
    return "books";
  }

}
