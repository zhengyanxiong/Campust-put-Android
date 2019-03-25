package com.example.tome.module_shop_mall.bean;


public class LoginBean extends ObjectBeans {
    private String username;
    private String password;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
