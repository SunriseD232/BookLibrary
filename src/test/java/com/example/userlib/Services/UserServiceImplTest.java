package com.example.userlib.Services;

import com.example.userlib.Impl.GiveAway.BookGivenAwayImpl;
import com.example.userlib.Impl.User.ROLE;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Repository.BookGiveAwayRepository;
import com.example.userlib.Repository.UserRepository;
import com.example.userlib.Services.BookingService;
import com.example.userlib.Services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private BookGiveAwayRepository bookGiveAwayRepository;

  @Mock
  private BookingService bookingService;

  @InjectMocks
  private UserServiceImpl userService;

  private UserImpl user;

  @BeforeEach
  void setUp() {
    user = new UserImpl();
    user.setId(1L);
    user.setUsername("testuser");
    user.setPassword("testpassword");
    user.setIsBlocked(false);
    user.setStrike(0);
    user.setRole(ROLE.USER);
  }

  @Test
  void testSaveUser() {
    when(passwordEncoder.encode("testpassword")).thenReturn("encodedPassword");
    System.out.println(user);
    userService.saveUser(user);

    assertEquals("encodedPassword", user.getPassword());
    verify(userRepository).save(user);
  }

  @Test
  void testFindUserByUsername() {
    when(userRepository.findByUsername("testuser")).thenReturn(user);

    UserImpl foundUser = userService.findUserByUsername("testuser");

    assertEquals(user, foundUser);
  }

  @Test
  void testStrikeUser() {
    UserImpl user1 = new UserImpl();
    user1.setUsername("user1");
    user1.setStrike(4);
    user1.setIsBlocked(false);

    UserImpl user2 = new UserImpl();
    user2.setUsername("user2");
    user2.setStrike(3);
    user2.setIsBlocked(false);

    when(bookGiveAwayRepository.findAllWhereReturnDateMore(any(LocalDate.class)))
        .thenReturn(Arrays.asList(
            new BookGivenAwayImpl(user1, null, LocalDate.now().minusDays(10), LocalDate.now()),
            new BookGivenAwayImpl(user2, null, LocalDate.now().minusDays(15), LocalDate.now())
        ));

    userService.strikeUser();

    assertEquals(5, user1.getStrike());
    assertTrue(user1.getIsBlocked());
    assertEquals(4, user2.getStrike());
    assertFalse(user2.getIsBlocked());

    verify(userRepository, times(1)).save(user1);
  }

  @Test
  void testBlockUser() {
    userService.blockUser(user);

    assertTrue(user.getIsBlocked());
    verify(bookingService).deleteBookings(user);
  }
}
