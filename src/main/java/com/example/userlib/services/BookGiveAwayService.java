package com.example.userlib.Services;

import com.example.userlib.Impl.GiveAway.BookGivenAwayImpl;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.BookingRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookGiveAwayService {

  @Autowired
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private BookRepository bookRepository;

  public void giveAway(UserImpl user, Long BookingId) {
    Book book = bookRepository.findFirstById(
        bookingRepository.findFirstById(BookingId).getBook().getId());
    bookingRepository.deleteById(BookingId);
    LocalDate now = LocalDate.now();
    BookGivenAwayImpl bookGivenAway = new BookGivenAwayImpl(user, book, now, now.plusWeeks(5));
    bookGiveAwayRepository.save(bookGivenAway);
  }

  public List<BookGivenAwayImpl> findGiveAwayByUser(UserImpl user) {
    return bookGiveAwayRepository.findByUser(user);
  }
}
