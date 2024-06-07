package com.example.userlib.Services;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookingRepository;
import com.example.userlib.sql.UploadBookSQL;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  private final BookGiveAwayRepository bookGiveAwayRepository;

  private final BookingRepository bookingRepository;

  private final JdbcTemplate jdbcTemplate;


  public void uploadBook() {
    String script = UploadBookSQL.getScript();
    jdbcTemplate.execute(script);
  }

  public List<Book> findByTitle(String title) {
    return bookRepository.findAllByString(title);
  }


  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public void returnBook(Long bookGivenAwayId) {
    Book book = bookGiveAwayRepository.findBookGivenAwayById(bookGivenAwayId).getBook();
    book.setCount(book.getCount() + 1);
    bookRepository.save(book);
    bookGiveAwayRepository.deleteById(bookGivenAwayId);

  }

  public void returnBooks(Booking booking) {
    Book book = booking.getBook();
    book.setCount(book.getCount() + 1);
    bookingRepository.deleteById(booking.getId());
    bookRepository.save(book);
  }
}