package com.example.userlib.Services;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.GiveAway.BookGivenAwayImpl;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.BookingRepository;
import com.example.userlib.Services.BookGiveAwayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookGiveAwayServiceTest {

  @Mock
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Mock
  private BookingRepository bookingRepository;

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookGiveAwayService bookGiveAwayService;

  private UserImpl user;
  private Book book;

  @BeforeEach
  void setUp() {
    user = new UserImpl();
    book = new Book();
    book.setId(1L);
  }

  @Test
  void testGiveAway() {
    // Arrange
    when(bookingRepository.findFirstById(anyLong())).thenReturn(new Booking(user, book, LocalDate.now(), LocalDate.now().plusWeeks(2)));
    when(bookRepository.findFirstById(anyLong())).thenReturn(book);

    // Act
    bookGiveAwayService.giveAway(user, 1L);

    // Assert
    verify(bookingRepository).deleteById(1L);
    verify(bookGiveAwayRepository).save(any(BookGivenAwayImpl.class));
  }

  @Test
  void testFindGiveAwayByUser() {
    // Arrange
    List<BookGivenAwayImpl> giveAways = Arrays.asList(new BookGivenAwayImpl(), new BookGivenAwayImpl());
    when(bookGiveAwayRepository.findByUser(user)).thenReturn(giveAways);

    // Act
    List<BookGivenAwayImpl> result = bookGiveAwayService.findGiveAwayByUser(user);

    // Assert
    assertEquals(giveAways, result);
  }
}
