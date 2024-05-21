package com.example.userlib.repository;

import com.example.userlib.services.book.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByTitle(String title);
  Book findFirstById (Long id);
}