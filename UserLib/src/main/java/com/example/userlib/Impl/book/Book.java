package com.example.userlib.Impl.book;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "books")
public class Book {

  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column(nullable = false)
  private String title;

  @NonNull
  @Column(nullable = false)
  private String author;

  @NonNull
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date year = new Date();

  @NonNull
  @Column(nullable = false)
  private int pages;


  @NonNull
  @Column(nullable = false)
  private int count;

  public Book() {
  }


  public Book(@NonNull Long id, @NonNull String title, @NonNull String author,
      @NonNull Date year, @NonNull int pages, @NonNull int count) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.year = year;
    this.pages = pages;
    this.count = count;
  }
}