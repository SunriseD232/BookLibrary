package com.example.userlib.repository;

import com.example.userlib.implementation.GiveAway.BookGivenAwayImpl;
import com.example.userlib.implementation.User.UserImpl;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookGiveAwayRepository extends
    JpaRepository<BookGivenAwayImpl, Long>{
  List<BookGivenAwayImpl> findByUser(UserImpl user);
}
