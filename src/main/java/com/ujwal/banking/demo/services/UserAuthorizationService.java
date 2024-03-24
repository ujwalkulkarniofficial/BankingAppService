package com.ujwal.banking.demo.services;

import com.ujwal.banking.demo.exceptions.ResourceNotFoundException;
import com.ujwal.banking.demo.model.LoginRequest;
import com.ujwal.banking.demo.model.User;
import com.ujwal.banking.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserAuthorizationService {


    @Autowired
    UserRepository userRepository;

    public User auth(LoginRequest loginRequest) throws ResourceNotFoundException {
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this username :: " + loginRequest.getUsername()));
        if(new BCryptPasswordEncoder().matches(loginRequest.getPassword(),user.getPassword())) {
            String valueToEncode = loginRequest.getUsername() + ":" + loginRequest.getPassword();
            String authToken = "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
            user.setPassword(null);
            user.setToken(authToken);
            return user;
        }
        throw new ResourceNotFoundException("Password provided does not match with records");
    }

}
