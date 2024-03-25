package com.example.itemdatamanagement.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class InsertUserForm {

    @Length(min = 1, max = 50, message = "Please input between 1 and 50 characters.")
    private String name;

    @Email(message = "error.mail.format")
    private String mailAddress;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$", message = "Please input 8 or more characters and up to 20 characters, including uppercase letters, lowercase letters, and half-width numbers")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "InsertUser [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
    }

}
