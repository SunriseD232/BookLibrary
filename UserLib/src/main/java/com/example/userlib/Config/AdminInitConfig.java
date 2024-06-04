package com.example.userlib.Config;

import com.example.userlib.Services.CustomUserDetailsService;
import com.example.userlib.Services.UserServiceImpl;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AdminInitConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserServiceImpl userService;

  @PostConstruct
    public void checkAdmin() {
      if (userService.findUserByUsername("admin") == null) {
        customUserDetailsService.uploadAdmin();
    }
  }
}
