package com.example.userlib.Services;

import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.BookingRepository;
import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookService bookService;

  public void bookBook(UserImpl user, Long bookId) {
    if (user.getIsBlocked() != Boolean.TRUE) {
      Book book = bookRepository.findFirstById(bookId);
      if (book != null && book.getCount() > 0) {
        book.setCount(book.getCount() - 1);
        bookRepository.save(book);
        LocalDate now = LocalDate.now();
        Booking booking = new Booking(user, book, now, now.plusWeeks(2));
        bookingRepository.save(booking);
      }
    }
  }

  public List<Booking> findBookingsByUser(UserImpl user) {
    return bookingRepository.findByUser(user);
  }

  public void deleteBookings(UserImpl user) {
    List<Booking> bookings = bookingRepository.findByUser(user);
    if (bookings.size() != 0) {
      for (Booking booking : bookings) {
        bookService.returnBookByBlocked(booking);
      }
    }
  }
}