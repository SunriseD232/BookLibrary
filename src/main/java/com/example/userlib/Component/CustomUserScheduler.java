package com.example.userlib.Component;

import com.example.userlib.Services.CustomUserDetailsService;
import com.example.userlib.Services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CustomUserScheduler {

  private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

  @Autowired
  private UserServiceImpl userService;

  @Scheduled(cron = "0 0 0 * * ?")
  public void checkAndModifyDatabase() {
    logger.info("Start check user");
    userService.strikeUser();
  }
}