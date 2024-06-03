package com.example.userlib.Services;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.BookRepository;
import com.example.userlib.Impl.book.Book;
import com.example.userlib.Repository.BookingRepository;
import com.example.userlib.sql.UploadBookSQL;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

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

  public void returnBook(String username, Long bookGivenAwayId) {
    Book book = bookGiveAwayRepository.findBookGivenAwayById(bookGivenAwayId).getBook();
    book.setCount(book.getCount() + 1);
    bookRepository.save(book);
    bookGiveAwayRepository.deleteById(bookGivenAwayId);

  }

  public void returnBookByBlocked(Booking booking) {
    Book book = booking.getBook();
    book.setCount(book.getCount() + 1);
    bookingRepository.deleteById(booking.getId());
    bookRepository.save(book);
  }
}