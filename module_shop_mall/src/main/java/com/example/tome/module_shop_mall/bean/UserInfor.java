package com.example.tome.module_shop_mall.bean;

import java.util.Date;

/**
 * Author: created by Bernie on 2019/3/25
 **/
public class UserInfor {
    private Integer userId;

    private Integer schoolId;

    private String studentId;

    private String password;

    private String username;

    private String headImag;

    private String phoneNum;

    private String sex;

    private String email;

    private String idCard;

    private String stuCardFront;

    private String stuCardBack;

    private Date registerDate;

    private Date userCreatedTime;

    private Date userUpdatedTime;

    private Integer userState;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadImag() {
        return headImag;
    }

    public void setHeadImag(String headImag) {
        this.headImag = headImag;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getStuCardFront() {
        return stuCardFront;
    }

    public void setStuCardFront(String stuCardFront) {
        this.stuCardFront = stuCardFront;
    }

    public String getStuCardBack() {
        return stuCardBack;
    }

    public void setStuCardBack(String stuCardBack) {
        this.stuCardBack = stuCardBack;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUserCreatedTime() {
        return userCreatedTime;
    }

    public void setUserCreatedTime(Date userCreatedTime) {
        this.userCreatedTime = userCreatedTime;
    }

    public Date getUserUpdatedTime() {
        return userUpdatedTime;
    }

    public void setUserUpdatedTime(Date userUpdatedTime) {
        this.userUpdatedTime = userUpdatedTime;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "UserInfor{" +
                "userId=" + userId +
                ", schoolId=" + schoolId +
                ", studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", headImag='" + headImag + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", idCard='" + idCard + '\'' +
                ", stuCardFront='" + stuCardFront + '\'' +
                ", stuCardBack='" + stuCardBack + '\'' +
                ", registerDate=" + registerDate +
                ", userCreatedTime=" + userCreatedTime +
                ", userUpdatedTime=" + userUpdatedTime +
                ", userState=" + userState +
                '}';
    }
}
