package com.example.swagger.dao;

import com.example.swagger.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getUsers();

    void createUser(User user);

    void createWithList(List<User> users);

    List<User> getByUserName(String username);

    void updateUser(User user);

    void deleteUser(long id);
    User getById(long id);

    boolean isExist(User user);


}
