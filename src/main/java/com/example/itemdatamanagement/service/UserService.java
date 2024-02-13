package com.example.itemdatamanagement.service;

import java.util.List;

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

    public User findByMailAddressAndPassword(String mailAddress, String password) {
        return userRepository.findByMailAddressAndPassword(mailAddress, password);
    }

    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }

    public void updateUser(User user) {
        userRepository.UpdateUser(user);
    }

    public User findByMailAddress(String mailAddress) {
        return userRepository.findByMailAddress(mailAddress);
    }
}
