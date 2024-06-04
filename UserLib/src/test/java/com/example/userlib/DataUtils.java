package com.example.userlib;

import com.example.userlib.Impl.User.ROLE;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import java.util.Date;

public class DataUtils {
  public static UserImpl createTestUser() {
    UserImpl user = new UserImpl();
    user.setId(1L);
    user.setUsername("test");
    user.setPassword("test");
    user.setRole(ROLE.USER);
    return user;
  }

  public static Book createTestBook(){
    Book book = new Book(1L, "test", "test", new Date(), 2, 1 );
    return book;
  }

}
