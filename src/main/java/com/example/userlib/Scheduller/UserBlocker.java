//package com.example.userlib.Scheduller;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserBlocker {
//
//  @Scheduled(cron = "0 0 0 * * *") // Запускать ежедневно в полночь
//  public void checkAndModifyDatabase() {
//
//
//    if (conditionIsMet()) {
//      updateDatabaseValue();
//    }
//  }
//
//  private boolean conditionIsMet() {
//    // Логика проверки условия в базе данных
//    return true; // Пример условия
//  }
//
//  private void updateDatabaseValue() {
//    // Логика изменения значения в базе данных
//  }
//}
