package com.example.userlib.repository;

import com.example.userlib.implementation.User.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserImpl, Long> {
  UserImpl findByUsername(String login);
}
