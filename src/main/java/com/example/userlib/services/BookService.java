package com.example.userlib.services;

import com.example.userlib.implementation.Booking.Booking;
import com.example.userlib.implementation.User.UserImpl;
import com.example.userlib.repository.BookGiveAwayRepository;
import com.example.userlib.repository.BookRepository;
import com.example.userlib.implementation.book.Book;
import com.example.userlib.repository.BookingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Autowired
  private BookingRepository bookingRepository;

  public List<Book> findByTitle(String title) {
    return bookRepository.findAllByString(title);
  }


  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public void returnBook(String username, Long bookGivenAwayId) {
    Book book = bookGiveAwayRepository.findBookGivenAwayById(bookGivenAwayId).getBook();
    book.setCount(book.getCount() + 1);
    bookRepository.save(book);
    bookGiveAwayRepository.deleteById(bookGivenAwayId);

  }

  public void returnBookByBlocked(UserImpl user, Long bookingId) {
    List<Booking> bookings = bookingRepository.findByUser(user);
    for (Booking booking : bookings) {
      Book book = booking.getBook();

      book.setCount(book.getCount() + 1);
      bookRepository.save(book);
      bookingRepository.deleteById(booking.getId());

    }

  }
}