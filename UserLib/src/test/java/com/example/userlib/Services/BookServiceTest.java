package com.example.userlib.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.BookingRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Mock
  private BookingRepository bookingRepository;

  @InjectMocks
  private BookService bookService;


  private UserImpl user;
  private Book book;
  private String testString;
  private Long Id;
  private BookGivenAway bookGivenAway;

  @BeforeEach
  void setUp() {

    user = new UserImpl();
    book = new Book(1L, "test", "test", new Date(), 1, 1);
    bookGivenAway = new BookGivenAway(user,book, LocalDate.now(), LocalDate.now());
    testString = "test";
    Id = 1L;
  }

  @Test
  void testFindByTitle()  {
    List<Book> books = new ArrayList<>();
    books.add(book);

    when(bookRepository.findAllByString(testString)).thenReturn(Arrays.asList(book));
    List<Book> result = bookService.findByTitle(testString);

    assertEquals(result, books);
  }

  @Test
  void testFindAll() {
    List<Book> books = new ArrayList<>();
    books.add(book);

    when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
    List<Book> result = bookService.findAll();

    assertEquals(result, books);
  }

  @Test
  void testReturnBook(){
    when(bookGiveAwayRepository.findBookGivenAwayById(Id)).thenReturn(
        bookGivenAway
    );

    bookService.returnBook(testString, Id);

    verify(bookRepository).save(book);
    verify(bookGiveAwayRepository).deleteById(Id);
  }

  @Test
  void testReturnBookByBlocked(){
    Booking booking = new Booking(user, book, LocalDate.now(), LocalDate.now());

    bookService.returnBooks(booking);

    verify(bookingRepository).deleteById(booking.getId());
    verify(bookRepository).save(book);
  }
}
