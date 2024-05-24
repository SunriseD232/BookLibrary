package com.example.userlib.services;

import com.example.userlib.repository.UserRepository;
import com.example.userlib.implementation.User.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl{
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserImpl saveUser(UserImpl user){
    user.setPassword(passwordEncoder.encode((user.getPassword())));
    return userRepository.save(user);
  }

  public UserImpl findUserByUsername(String username){
    return userRepository.findByUsername(username);
  }

}
