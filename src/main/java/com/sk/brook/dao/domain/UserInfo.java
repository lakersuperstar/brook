package com.sk.brook.dao.domain;

/**
 * Created by songk on 17/11/22.
 */
public class UserInfo {
    /**
     * 用户账号，全局唯一
     */
    private String pin;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 登陆密码
     */
    private String pwd;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "pin='" + pin + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
