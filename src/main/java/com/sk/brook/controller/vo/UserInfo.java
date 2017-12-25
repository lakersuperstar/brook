package com.sk.brook.controller.vo;

/**
 * Created by Administrator on 2017/12/22.
 */
public class UserInfo {

    private String userName;
    private String password;
    private String meIndex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMeIndex() {
        return meIndex;
    }

    public void setMeIndex(String meIndex) {
        this.meIndex = meIndex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", meIndex='" + meIndex + '\'' +
                '}';
    }
}
