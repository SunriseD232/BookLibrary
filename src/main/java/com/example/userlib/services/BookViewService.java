package com.example.userlib.Services;

import com.example.userlib.Impl.book.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookViewService {
  private final BookService bookService;

  public List<Book> getBook(String keyword){
    List<Book> books;
    if (keyword == null || keyword.isEmpty()) {
      books = bookService.findAll();
    } else {
      books = bookService.findByTitle(keyword);
    }
    books = books.stream()
        .sorted(Comparator.comparing(Book::getTitle))
        .toList();
    return books;
  }

}
