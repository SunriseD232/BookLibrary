package com.example.userlib.Impl;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BookingTest {

  @Mock
  private UserImpl user;

  @Mock
  private Book book;

  @InjectMocks
  private Booking booking;

  @Test
  void testBookingConstructor() {
    // Arrange
    LocalDate bookingDate = LocalDate.now();
    LocalDate endBookingDate = LocalDate.now().plusWeeks(2);

    // Act
    Booking booking = new Booking(user, book, bookingDate, endBookingDate);

    // Assert
    assertNotNull(booking);
    assertEquals(user, booking.getUser());
    assertEquals(book, booking.getBook());
    assertEquals(bookingDate, booking.getBookingDate());
    assertEquals(endBookingDate, booking.getEndBookingDate());
  }

  @Test
  void testBookingDefaultConstructor() {
    // Act
    Booking booking = new Booking();

    // Assert
    assertNotNull(booking);
  }
}
