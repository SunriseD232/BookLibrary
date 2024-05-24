package com.example.userlib.repository;

import com.example.userlib.implementation.Booking.Booking;
import com.example.userlib.implementation.User.UserImpl;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByUser(UserImpl user);
  Booking findFirstById(Long id);
}