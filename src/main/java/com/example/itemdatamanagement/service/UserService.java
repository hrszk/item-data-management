package com.example.itemdatamanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.User;
import com.example.itemdatamanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public User findByMailAddressAndPassword(String mailAddress,String password){
        return userRepository.findByMailAddressAndPassword(mailAddress,password);
    }
}
