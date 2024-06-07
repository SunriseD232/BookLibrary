package com.example.userlib.Services;

import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Repository.BookingRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookGiveAwayService {

  private final BookGiveAwayRepository bookGiveAwayRepository;

  private final BookingRepository bookingRepository;

  private final BookRepository bookRepository;


  public void giveAway(UserImpl user, Long BookingId) {
    Book book = bookRepository.findFirstById(
        bookingRepository.findFirstById(BookingId).getBook().getId());
    bookingRepository.deleteById(BookingId);
    LocalDate now = LocalDate.now();
    BookGivenAway bookGivenAway = new BookGivenAway(user, book, now, now.plusWeeks(5));
    bookGiveAwayRepository.save(bookGivenAway);
  }

  public List<BookGivenAway> findGiveAwayByUser(UserImpl user) {
    return bookGiveAwayRepository.findByUser(user);
  }
}
