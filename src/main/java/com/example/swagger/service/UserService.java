package com.example.swagger.service;


import com.example.swagger.dao.UserDao;
import com.example.swagger.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean addUser(User user){
        if(!userDao.isExist(user)){
            userDao.createUser(user);
            return true;
        }else return false ;
    }

    public  void addUsers(List<User> users){
        userDao.createWithList(users);
    }

    public List<User> getByUsername(String username){
        return userDao.getByUserName(username);
    }

    public boolean updateUser(User user){
        if (userDao.isExist(user)) {
            userDao.updateUser(user);
            return true;
        }else return false;
    }

    public boolean deleteUser(long id){
        if (userDao.isExist(userDao.getById(id))){
            userDao.deleteUser(id);
            return true;
        }else return false;
    }

    public List<User> getAll(){
        return userDao.getUsers();
    }


}
