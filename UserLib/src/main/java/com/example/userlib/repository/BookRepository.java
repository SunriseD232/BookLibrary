package com.example.userlib.Repository;

import com.example.userlib.Impl.book.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("SELECT b FROM Book b WHERE b.title LIKE %:searchString%")
  List<Book> findAllByString(@Param("searchString") String title);

  Book findFirstById(Long id);
}