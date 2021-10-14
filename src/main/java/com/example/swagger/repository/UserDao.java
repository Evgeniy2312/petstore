package com.example.swagger.repository;

import com.example.swagger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> getUsersByUsername(String username);
}
