package com.example.userlib.Impl;

import com.example.userlib.Impl.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

  private Book book;

  @BeforeEach
  void setUp() {
    book = new Book();
  }

  @Test
  void testConstructor() {
    assertNotNull(book.getYear());
    assertEquals(0, book.getPages());
    assertEquals(0, book.getCount());
  }

  @Test
  void testSetAndGetTitle() {
    book.setTitle("Book Title");
    assertEquals("Book Title", book.getTitle());
  }

  @Test
  void testSetAndGetAuthor() {
    book.setAuthor("John Doe");
    assertEquals("John Doe", book.getAuthor());
  }

  @Test
  void testSetAndGetYear() {
    Date date = new Date();
    book.setYear(date);
    assertEquals(date, book.getYear());
  }

  @Test
  void testSetAndGetPages() {
    book.setPages(100);
    assertEquals(100, book.getPages());
  }

  @Test
  void testSetAndGetCount() {
    book.setCount(10);
    assertEquals(10, book.getCount());
  }

  @Test
  void testNullableFields() {
    assertThrows(NullPointerException.class, () -> {
      Book nullBook = new Book();
      nullBook.setTitle(null);
      nullBook.setAuthor(null);
      nullBook.setYear(null);
    });

  }
}
