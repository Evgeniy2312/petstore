package com.example.swagger.controller;

import com.example.swagger.entity.User;
import com.example.swagger.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        if(userService.addUser(user)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }

    @PostMapping("/addUsers")
    public ResponseEntity<List<User>> createWithList( @RequestBody List<@Valid User> users){
        if(!users.isEmpty()){
            userService.addUsers(users);
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
        if(userService.updateUser(user)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        if(userService.deleteUser(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<List<User>> getByUsername( @PathVariable @NotNull @NotEmpty @NotBlank String username){
        if(!userService.getByUsername(username).isEmpty()){
            return new ResponseEntity<>(userService.getByUsername(username),HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        if(!userService.getAll().isEmpty()){
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
