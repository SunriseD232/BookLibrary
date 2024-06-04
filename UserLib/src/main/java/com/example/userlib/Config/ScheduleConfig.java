package com.example.userlib.Config;

import com.example.userlib.Component.CustomUserScheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduleConfig {

  @Bean
  public CustomUserScheduler userScheduler() {
    return new CustomUserScheduler();
  }
}