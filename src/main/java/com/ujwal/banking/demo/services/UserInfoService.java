package com.ujwal.banking.demo.services;

import com.ujwal.banking.demo.exceptions.ResourceNotFoundException;
import com.ujwal.banking.demo.model.User;
import com.ujwal.banking.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {


    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found for this id :: " + userId));

    }

}
