package com.example.tome.module_shop_mall.bean;


public class LoginBean extends ObjectBeans {
    private String username;
    private String password;
    private String studentId;

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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
