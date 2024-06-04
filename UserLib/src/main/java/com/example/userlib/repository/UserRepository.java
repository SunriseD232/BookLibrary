package com.example.userlib.Repository;

import com.example.userlib.Impl.User.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserImpl, Long> {

  UserImpl findByUsername(String login);
}
