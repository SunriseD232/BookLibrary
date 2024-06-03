package com.example.userlib.Services;

import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.ROLE;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.UserRepository;
import com.example.userlib.Impl.User.UserImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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


  public UserImpl saveUserWithEcnoder(UserImpl user) {
    user.setPassword(passwordEncoder.encode((user.getPassword())));
    return userRepository.save(user);
  }

  public UserImpl saveUser(UserImpl user) {
    return userRepository.save(user);
  }

  public UserImpl findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Boolean isAdmin(UserDetails userDetails){
    if(findUserByUsername(userDetails.getUsername()).getRole() == ROLE.ADMIN){
      return true;
    }
    else{
      return false;
    }
  }

  public List<UserImpl> findAllUsers(){
    return userRepository.findAll();
  }

  public void strikeUser() {
    List<UserImpl> usersExpired = findUserWithExpiredBook();
    for (UserImpl user : usersExpired) {
      user.setStrike(user.getStrike() + 1);
      if (user.getStrike() >= 5) {
        blockUser(user);
      }
      userRepository.save(user);

    }
  }

  public List<UserImpl> findUserWithExpiredBook() {
    LocalDate now = LocalDate.now();
    List<BookGivenAway> BookingReturnDateExpired = bookGiveAwayRepository.findAllWhereReturnDateMore(
        now);
    List<UserImpl> UserExpired = new ArrayList();
    for (com.example.userlib.Impl.GiveAway.BookGivenAway BookGivenAway : BookingReturnDateExpired) {
      UserExpired.add(BookGivenAway.getUser());
    }
    return UserExpired;
  }

  public void blockUser(UserImpl user) {
    user.setIsBlocked(Boolean.TRUE);
    if (bookingService.findBookingsByUser(user).size() != 0) {
      bookingService.deleteBookings(user);
    }
  }
}
