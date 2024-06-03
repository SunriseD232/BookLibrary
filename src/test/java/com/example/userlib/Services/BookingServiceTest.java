package com.example.userlib.Services;

import com.example.userlib.DataUtils;
import com.example.userlib.Impl.User.ROLE;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.BookingRepository;
import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

  @Mock
  private BookingRepository bookingRepository;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private BookService bookService;

  @InjectMocks
  private BookingService bookingService;

  @Test
  void testBookBook_UserNotBlocked_BookAvailable() {
    // Arrange
    UserImpl user = new UserImpl();
    user.setIsBlocked(false);
    Book book = new Book();
    book.setCount(1);
    when(bookRepository.findFirstById(anyLong())).thenReturn(book);

    // Act
    bookingService.bookBook(user, 1L);

    // Assert
    verify(bookRepository).save(book);
    assertEquals(0, book.getCount());
    verify(bookingRepository).save(any(Booking.class));
  }

  @Test
  void testBookBook_UserBlocked_BookNotBooked() {
    // Arrange
    UserImpl user = DataUtils.createTestUser();
    user.setIsBlocked(true);

    // Act
    bookingService.bookBook(user, 1L);

    // Assert
    verify(bookRepository, never()).save(any(Book.class));
    verify(bookingRepository, never()).save(any(Booking.class));
  }

  @Test
  void testBookBook_BookNotFound_BookNotBooked() {
    // Arrange
    UserImpl user = DataUtils.createTestUser();
    user.setIsBlocked(false);
    when(bookRepository.findFirstById(anyLong())).thenReturn(null);

    // Act
    bookingService.bookBook(user, 1L);

    // Assert
    verify(bookRepository, never()).save(any(Book.class));
    verify(bookingRepository, never()).save(any(Booking.class));
  }

  @Test
  void testFindBookingsByUser() {
    // Arrange
    UserImpl user = DataUtils.createTestUser();
    List<Booking> bookings = Arrays.asList(new Booking(), new Booking());
    when(bookingRepository.findByUser(user)).thenReturn(bookings);

    // Act
    List<Booking> result = bookingService.findBookingsByUser(user);

    // Assert
    assertEquals(bookings, result);
  }

//  @Test
//  void testDeleteBookings() {
//    // Arrange
//    UserImpl user = DataUtils.createTestUser();
//
//    Booking booking1 = new Booking();
//    booking1.setId(1L);
//
//    Booking booking2 = new Booking();
//    booking2.setId(2L);
//
//    List<Booking> bookings = Arrays.asList(booking1, booking2);
//    when(bookingRepository.findByUser(user)).thenReturn(bookings);
//
//    // Act
//    bookingService.deleteBookings(user);
//
//    // Assert
//    verify(bookService, times(2)).returnBookByBlocked(eq(user),  anyLong());
//    verify(bookingRepository).deleteById(user.getId());
//  }
}
