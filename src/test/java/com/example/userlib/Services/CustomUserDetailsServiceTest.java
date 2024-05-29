package com.example.userlib.Services;

import com.example.userlib.DataUtils;
import com.example.userlib.Repository.UserRepository;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.Services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private Logger logger;

  @InjectMocks
  private CustomUserDetailsService customUserDetailsService;

  @Test
  void testLoadUserByUsername_UserFound() {
    // Arrange
    String username = "testuser";
    UserImpl user = DataUtils.createTestUser();
    user.setUsername(username);
    when(userRepository.findByUsername(username)).thenReturn(user);

    // Act
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

    // Assert
    assertEquals(username, userDetails.getUsername());
    assertEquals("test", userDetails.getPassword());
    assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());

    }



  @Test
  void testLoadUserByUsername_UserNotFound() {
    // Arrange
    String username = "nonexistentuser";
    when(userRepository.findByUsername(username)).thenReturn(null);

    // Act & Assert
    UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
      customUserDetailsService.loadUserByUsername(username);
    });
    assertEquals("User not found", exception.getMessage());
  }
}
