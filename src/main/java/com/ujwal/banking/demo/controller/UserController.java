package com.ujwal.banking.demo.controller;

import com.ujwal.banking.demo.exceptions.ResourceNotFoundException;
import com.ujwal.banking.demo.model.User;
import com.ujwal.banking.demo.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {



    @Autowired
    UserInfoService userInfoService;



    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userInfoService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userInfoService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }


}
