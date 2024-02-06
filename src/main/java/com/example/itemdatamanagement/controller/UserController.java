package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.User;
import com.example.itemdatamanagement.form.InsertUserForm;
import com.example.itemdatamanagement.form.UpdateUserForm;
import com.example.itemdatamanagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "", "/" })
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/toPageUserRegister")
    public String toPageUserRegister(InsertUserForm form, Model model) {
        return "user/register";
    }

    @PostMapping("/insertUser")
    public String insertUser(@Validated InsertUserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/toPageUserRegister";
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setAuthority(0);

        userService.insertUser(user);
        return "user/login";
    }

    /**
     * ログイン画面の表示
     * 
     * @return ログイン画面
     */
    @GetMapping("/login")
    public String toPageUserLogin() {
        return "user/login";
    }

    /**
     * ログイン機能
     * 
     * @param mailAddress String型 メールアドレス
     * @param password    String型 パスワード
     * @param model       リクエストスコープ
     * @return
     */
    @PostMapping("/userLogin")
    public String userLogin(String mailAddress, String password, Model model) {
        User user = userService.findByMailAddressAndPassword(mailAddress, password);
        if (user == null) {
            model.addAttribute("error", "error:failed to login");
            return "user/login";
        } else {
            session.setAttribute("user", user);
            if (user.getAuthority() == 0) {
                return "redirect:/showItemList";
            } else {
                return "redirect:/showUserList";
            }
        }
    }

    /**
     * ユーザー一覧表示
     * 
     * @param model スコープの準備
     * @return ユーザー一覧画面
     */
    @GetMapping("/showUserList")
    public String showUserList(Model model) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        return "administrator/list";
    }

    /**
     * ユーザー詳細情報表示
     * 
     * @param id    ユーザーID
     * @param model スコープの準備
     * @return ユーザー詳細画面
     */
    @GetMapping("/showUserDetail")
    public String showUserDetail(Integer id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "administrator/detail";
    }

    @GetMapping("/toPageUserEdit")
    public String toPageUserEdit(Integer id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "administrator/edit";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        userService.deleteUser(id);
        return "redirect:/showUserList";
    }

    @PostMapping("/updateUser")
    public String updateUser(UpdateUserForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.updateUser(user);
        return "redirect:/showUserList";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("user");
        return "user/login";
    }
}
