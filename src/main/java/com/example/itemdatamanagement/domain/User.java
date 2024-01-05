package com.example.itemdatamanagement.domain;

public class User {
    private Integer id;
    private String name;
    private String mailAddress;
    private String password;
    private Integer authority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + ", authority=" + authority + "]";
    }

}
