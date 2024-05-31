package com.example.userlib.Component;

import com.example.userlib.Services.CustomUserDetailsService;
import com.example.userlib.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CustomUserScheduler {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;
  @Autowired
  private UserServiceImpl userService;

  @Scheduled(cron = "0 0 0 * * ?")
  public void checkAndModifyDatabase() {
    if (userService.findUserByUsername("admin") == null) {
      customUserDetailsService.uploadAdmin();
    }
  }
}