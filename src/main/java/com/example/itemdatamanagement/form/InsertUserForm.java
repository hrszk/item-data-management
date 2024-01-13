package com.example.itemdatamanagement.form;

import jakarta.validation.constraints.NotBlank;

public class InsertUserForm {

    @NotBlank(message = "error:may not be empty")
    private String name;

    @NotBlank(message = "error:may not be empty")
    private String mailAddress;

    @NotBlank(message = "error:may not be empty")
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
