package com.example.userlib.Services;

import com.example.userlib.Impl.book.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookViewService {
  @Autowired
  private BookService bookService;

  public List<Book> getBook(String keyword){
    List<Book> books;
    if (keyword == null || keyword.isEmpty()) {
      books = bookService.findAll();
    } else {
      books = bookService.findByTitle(keyword);
    }
    books = books.stream()
        .sorted(Comparator.comparing(Book::getTitle))
        .collect(Collectors.toList());
    return books;
  }

}
