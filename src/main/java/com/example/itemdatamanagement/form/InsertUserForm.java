package com.example.itemdatamanagement.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InsertUserForm {

    @Length(min = 1, max = 50, message = "Please input between 1 and 50 characters.")
    private String name;

    @Email(message = "error.mail.format")
    private String mailAddress;
    
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,20}$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
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
