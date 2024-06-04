package com.example.userlib.Repository;

import com.example.userlib.Impl.Booking.Booking;
import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Impl.book.Book;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findByUser(UserImpl user);

  Booking findFirstById(Long id);

  @Query("select b from Booking b where b.endBookingDate < :now")
  List<Booking> findAllWhereEndBookingDateMore(@Param("now") LocalDate now);


}