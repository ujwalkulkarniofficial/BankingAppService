package com.ujwal.banking.demo.controller;


import com.ujwal.banking.demo.model.LoginRequest;
import com.ujwal.banking.demo.model.User;
import com.ujwal.banking.demo.services.UserAuthorizationService;
import com.ujwal.banking.demo.services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserAuthorizationService userAuthorizationService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody User user) {
        try{
            var ret = userRegistrationService.registerNewUser(user);
            return ResponseEntity.ok().body(ret);
        } catch (Exception e){
            String msg = e.getMessage().contains("unique constraint \"uniqueuser\"") ? "User already exists" : e.getMessage();
            return ResponseEntity.internalServerError().body(msg);
        }

    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try{
            var ret = userAuthorizationService.auth(loginRequest);
            return ResponseEntity.ok().body(ret);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}
