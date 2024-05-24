package com.example.userlib.services;

import com.example.userlib.repository.BookGiveAwayRepository;
import com.example.userlib.repository.BookRepository;
import com.example.userlib.repository.BookingRepository;
import com.example.userlib.implementation.Booking.Booking;
import com.example.userlib.implementation.User.UserImpl;
import com.example.userlib.implementation.book.Book;
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
  private BookGiveAwayRepository bookGiveAwayRepository;

  public void bookBook(UserImpl user, Long bookId){
    Book book = bookRepository.findFirstById(bookId);
    if (book != null && book.getCount() > 0){
      book.setCount(book.getCount()-1);
      bookRepository.save(book);
      LocalDate now = LocalDate.now();
      Booking booking = new Booking(user, book, now, now.plusWeeks(2));
      bookingRepository.save(booking);
    }
  }



  public List<Booking> findBookingsByUser(UserImpl user){
    return bookingRepository.findByUser(user);
  }
}