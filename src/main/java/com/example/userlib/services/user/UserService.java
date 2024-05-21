package com.example.userlib.services.user;

import com.example.userlib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;


  public void saveUser(UserImpl user) {
    userRepository.save(user);
  }

  public boolean isUsernameExists(String username) {
    return userRepository.findByUsername(username) != null;
  }

  public boolean isEmailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }

  public UserImpl authenticateUser(String username, String password) {
    UserImpl user = userRepository.findByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
      return user;
    }
    return null;
  }

  public UserImpl findById(Long userId) {
    return userRepository.findById(userId).orElse(null);
  }

  public UserImpl findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}