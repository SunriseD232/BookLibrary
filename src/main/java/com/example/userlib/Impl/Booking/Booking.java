package com.example.userlib.Impl.Booking;

import com.example.userlib.Impl.book.Book;
import com.example.userlib.Impl.User.UserImpl;
import java.time.LocalDate;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserImpl user;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  private LocalDate bookingDate;
  private LocalDate endBookingDate;

  public Booking(UserImpl user, Book book, LocalDate bookingDate, LocalDate endBookingDate) {
    this.user = user;
    this.book = book;
    this.bookingDate = bookingDate;
    this.endBookingDate = endBookingDate;
  }

  public Booking() {

  }
}