package com.example.userlib.Scheduller;

import com.example.userlib.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserBlocker {

  @Autowired
  UserServiceImpl userService;

  //Каждый день, за каждую не сданную книгу будет прилетать страйк, 5 страйков - блокировка пользователя
  @Scheduled(cron = "0 0 0 * * *") // Запускать ежедневно в полночь
  public void checkAndModifyDatabase() {
    userService.strikeUser();
  }
}
