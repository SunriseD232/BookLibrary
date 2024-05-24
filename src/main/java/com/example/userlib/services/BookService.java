package com.example.userlib.services;

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

  public List<Book> findByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  public List<Book> findAll(){
    return bookRepository.findAll();
  }

  public void returnBook(String username, Long bookGivenAwayId){
    Book book = bookGiveAwayRepository.findBookGivenAwayById(bookGivenAwayId).getBook();
    book.setCount(book.getCount()+1);
    bookRepository.save(book);
    bookGiveAwayRepository.deleteById(bookGivenAwayId);

  }
}