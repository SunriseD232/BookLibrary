package com.example.userlib.services.Booking;

import com.example.userlib.repository.BookingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
  @Autowired
  private BookingRepository bookingRepository;

  public void saveBooking(Booking booking) {
    bookingRepository.save(booking);
  }

  public List<Booking> getAllBookings()
  {
    return bookingRepository.findAll();
  }

}