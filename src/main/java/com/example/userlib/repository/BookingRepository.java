package com.example.userlib.repository;

import com.example.userlib.services.Booking.Booking;
import com.example.userlib.services.User.UserImpl;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByUser(UserImpl user);
}