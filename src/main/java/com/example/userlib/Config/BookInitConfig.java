package com.example.userlib.Config;

import com.example.userlib.Impl.book.Book;
import com.example.userlib.Services.BookService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookInitConfig {
  @Autowired
  private BookService bookService;

  @PostConstruct
  public void initBooks() {
  List<Book> checkBookData = bookService.findAll();
    if (checkBookData.size() == 0) {
      bookService.uploadBook();
    }
  }
}
