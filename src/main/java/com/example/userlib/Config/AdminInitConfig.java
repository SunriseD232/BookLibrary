package com.example.userlib.Config;

import com.example.userlib.Services.CustomUserDetailsService;
import com.example.userlib.Services.UserServiceImpl;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class AdminInitConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final UserServiceImpl userService;


  @PostConstruct
    public void checkAdmin() {
      if (userService.findUserByUsername("admin") == null) {
        customUserDetailsService.uploadAdmin();
    }
  }
}
