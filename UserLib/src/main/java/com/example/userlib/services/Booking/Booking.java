package com.example.userlib.services.Booking;

import com.example.userlib.services.book.Book;
import com.example.userlib.services.user.UserImpl;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private UserImpl user;

  @ManyToMany
  private List<Book> books;
}