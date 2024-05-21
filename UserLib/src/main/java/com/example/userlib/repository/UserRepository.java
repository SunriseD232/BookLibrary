package com.example.userlib.repository;

import com.example.userlib.services.user.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserImpl, Long> {
  UserImpl findByUsername(String username);
  UserImpl findByEmail(String email);
}