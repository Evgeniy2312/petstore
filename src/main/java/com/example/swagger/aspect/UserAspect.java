package com.example.swagger.aspect;


import com.example.swagger.entity.Pet;
import com.example.swagger.entity.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UserAspect {


    private final Logger logger = LoggerFactory.getLogger(UserAspect.class);


    @Pointcut("execution(public * com.example.swagger.controller.UserController.createUser(..)) && args(user)")
    public void addUser(User user) {}

    @Pointcut("execution(public * com.example.swagger.controller.UserController.createWithList(..)) && args(users)")
    public void addUsers(List<User> users) {}

    @Pointcut("execution(public * com.example.swagger.controller.UserController.updateUser(..)) && args(user)")
    public void update(User user) {}

    @Pointcut("execution(public * com.example.swagger.controller.UserController.deleteUser(..)) && args(id)")
    public void delete(long id) {}

    @Pointcut("execution(public * com.example.swagger.controller.UserController.getByUsername(..)) && args(username)")
    public void getByUsername(String username) {}

    @Pointcut("execution(public * com.example.swagger.controller.UserController.getAll())")
    public void getAll() {}


    @After(value = "addUser(user)", argNames = "user")
    public void add(User user){
        logger.info("User with name - {} was added", user.getFirstName() );
    }

    @After(value = "addUsers(users)", argNames = "users")
    public void users(List<User> users){
        logger.info("List with user in amount- {} was added ", users.size() );
    }

    @After(value = "delete(id)", argNames = "id")
    public void del(long id){
        logger.info("User with id - {} was received", id);
    }

    @After(value = "getByUsername(username)", argNames = "username")
    public void getUsername(String username){
        logger.info("User with username - {} was received", username);
    }

    @After(value = "update(user)", argNames = "user")
    public void upd(User user){
        logger.info("User with name - {} was updated", user.getFirstName() );
    }

    @After(value = "getAll()")
    public void all(){
        logger.info("All users were received");
    }



}
