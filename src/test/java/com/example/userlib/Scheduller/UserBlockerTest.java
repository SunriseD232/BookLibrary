package com.example.userlib.Scheduller;

import com.example.userlib.Services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.Mockito.*;

@SpringBootTest
@EnableScheduling
@DirtiesContext
class UserBlockerTest {

  @Autowired
  private UserBlocker userBlocker;

  @MockBean
  private UserServiceImpl userService;

  @Test
  void testCheckAndModifyDatabase() {
    // Arrange - нет необходимости, так как используется mock для UserServiceImpl

    // Act
    userBlocker.checkAndModifyDatabase();

    // Assert
    verify(userService, times(1)).strikeUser(); // Проверяем, что метод strikeUser() был вызван один раз
  }
}
