package com.example.userlib.Services;

import com.example.userlib.Repository.UserRepository;
import com.example.userlib.Impl.User.UserImpl;
import com.example.userlib.sql.UploadAdminSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserImpl user = userRepository.findByUsername(username);
    if (user == null) {
      logger.error("User not found with name: {}", username);
      throw new UsernameNotFoundException("User not found");
    }
    UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
    builder.password(user.getPassword());
    builder.roles("USER");
    logger.info("User found with name: {}", username);
    return builder.build();
  }

  public void uploadAdmin(){
    String script = UploadAdminSQL.UploadAdminScript();
    jdbcTemplate.execute(script);
  }
}
