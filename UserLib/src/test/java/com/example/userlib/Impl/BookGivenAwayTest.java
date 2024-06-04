package com.example.userlib.Impl;

import com.example.userlib.DataUtils;
import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BookGivenAwayTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  @Test
  void testBookGivenAwayImpl() {
    // Arrange
    UserImpl user = DataUtils.createTestUser();
    userRepository.save(user);

    Book book = new Book();
    book.setTitle("Test Book");
    book.setAuthor("Test Author");
    bookRepository.save(book);

    LocalDate givenAwayDate = LocalDate.now();
    LocalDate returnDate = LocalDate.now().plusWeeks(2);

    // Act
    BookGivenAway bookGivenAway = new BookGivenAway(user, book, givenAwayDate, returnDate);
    entityManager.persist(bookGivenAway);
    entityManager.flush();

    // Assert
    BookGivenAway foundBookGivenAway = bookGiveAwayRepository.findById(bookGivenAway.getId()).orElse(null);
    assertNotNull(foundBookGivenAway);
    assertEquals(user, foundBookGivenAway.getUser());
    assertEquals(book, foundBookGivenAway.getBook());
    assertEquals(givenAwayDate, foundBookGivenAway.getGivenAwayDate());
    assertEquals(returnDate, foundBookGivenAway.getReturnDate());
  }
}
