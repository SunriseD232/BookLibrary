package com.example.userlib.services.book;

import com.example.userlib.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  public List<Book> findByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  public List<Book> findAll(){
    return bookRepository.findAll();
  }

  public Book findById(Long bookId) {
    return bookRepository.findFirstById(bookId);
  }
}