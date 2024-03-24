package com.ujwal.banking.demo.services;

import com.ujwal.banking.demo.model.User;
import com.ujwal.banking.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountDetailsService accountDetailsService;

    public User registerNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        var ret = userRepository.saveAndFlush(user);
        accountDetailsService.createNewAccount(user);
        return ret;
    }


}
