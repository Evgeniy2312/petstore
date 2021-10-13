package com.example.swagger.dao;

import com.example.swagger.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDaoImpl implements UserDao {

    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void createWithList(List<User> usersList) {
        for (User user: usersList) {
            if(!users.contains(user)){
                users.add(user);
            }
        }
    }

    @Override
    public List<User> getByUserName(String username) {
        return users.stream().filter(x -> x.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user) {
        users.set(users.indexOf(user), user);
    }

    @Override
    public void deleteUser(long id) {
        users.removeIf(x -> x.getId() == id);
    }

    @Override
    public User getById(long id) {
        return users.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    @Override
    public boolean isExist(User user) {
        return users.contains(user);
    }
}
