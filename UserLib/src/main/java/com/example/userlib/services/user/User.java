package com.example.userlib.services.user;

import java.util.Collection;

public interface User {
  String getUsername();
  void setUsername(String username);

  String getPassword();
  void setPassword(String password);

  String getEmail();
  void setEmail(String email);

  String getFullName();
  void setFullName(String fullName);

}