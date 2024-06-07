package com.example.userlib.Config;

import com.example.userlib.Impl.book.Book;
import com.example.userlib.Services.BookService;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BookInitConfig {

  private final BookService bookService;


  @PostConstruct
  public void initBooks() {
  List<Book> checkBookData = bookService.findAll();
    if (checkBookData.isEmpty()) {
      bookService.uploadBook();
    }
  }
}
