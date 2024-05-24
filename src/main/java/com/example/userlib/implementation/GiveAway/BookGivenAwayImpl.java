package com.example.userlib.implementation.GiveAway;

import com.example.userlib.implementation.User.UserImpl;
import com.example.userlib.implementation.book.Book;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BookGivenAwayImpl {
  @Id
  @GeneratedValue(strategy  = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  private UserImpl user;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  private LocalDate givenAwayDate;
  private LocalDate returnDate;

  public BookGivenAwayImpl(){
  }

  public BookGivenAwayImpl(UserImpl user, Book book, LocalDate givenAwayDate, LocalDate returnDate){
    this.user = user;
    this.book =book;
    this.givenAwayDate  = givenAwayDate;
    this.returnDate = returnDate;
  }


}
