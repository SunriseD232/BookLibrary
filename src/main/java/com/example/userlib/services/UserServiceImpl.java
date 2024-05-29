package com.example.userlib.Services;

import com.example.userlib.Impl.GiveAway.BookGivenAwayImpl;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.UserRepository;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.sql.UploadAdminSQL;
import com.example.userlib.sql.UploadBookSQL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private BookGiveAwayRepository bookGiveAwayRepository;
  @Autowired
  private BookingService bookingService;


  public UserImpl saveUser(UserImpl user) {
    user.setPassword(passwordEncoder.encode((user.getPassword())));
    return userRepository.save(user);
  }

  public UserImpl findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public void strikeUser() {
    List<UserImpl> users = findUserWithExpiredBook();
    for (UserImpl user : users) {
      user.setStrike(user.getStrike() + 1);
      if (user.getStrike() >= 5) {
        blockUser(user);
        userRepository.save(user);
      }
    }
  }

  public List<UserImpl> findUserWithExpiredBook() {
    LocalDate now = LocalDate.now();
    List<BookGivenAwayImpl> BookingReturnDateExpired = bookGiveAwayRepository.findAllWhereReturnDateMore(
        now);
    List<UserImpl> UserExpired = new ArrayList();
    for (BookGivenAwayImpl BookGivenAway : BookingReturnDateExpired) {
      UserExpired.add(BookGivenAway.getUser());
    }
    return UserExpired;
  }

  public void blockUser(UserImpl user) {
    user.setIsBlocked(Boolean.TRUE);
    bookingService.deleteBookings(user);
  }
}
