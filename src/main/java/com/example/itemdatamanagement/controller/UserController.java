package com.example.itemdatamanagement.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.User;
import com.example.itemdatamanagement.form.InsertUserForm;
import com.example.itemdatamanagement.service.UserService;

@Controller
@RequestMapping({ "", "/" })
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/toPageUserRegister")
    public String toPageUserRegister() {
        return "user/register";
    }

    @PostMapping("/insertUser")
    public String insertUser(InsertUserForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);

        userService.insertUser(user);
        return "user/login";
    }
}
