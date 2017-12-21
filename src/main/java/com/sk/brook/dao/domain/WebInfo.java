package com.sk.brook.dao.domain;

import java.util.Date;

/**
 * Created by songk on 17/11/22.
 */
public class WebInfo {
    /**
     * 主键
     */
    private int id;
    /**
     * 网站访问主页
     */
    private String webMainIndex;
    /**
     * 网站访问我的主页
     */
    private String webMeIndex;
    /**
     * 用户账号
     */
    private String pin;
    /**
     * 记录创建日期
     */
    private Date createDate;
    /**
     * 是否有效
     */
    private int yn;

    /**
     * 网站登录界面
     */
    private String webLogin;

    /**
     * 用户登录用户名
     */
    private String userName;

    /**
     * 用户登录密码
     */
    private String userPwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebMainIndex() {
        return webMainIndex;
    }

    public void setWebMainIndex(String webMainIndex) {
        this.webMainIndex = webMainIndex;
    }

    public String getWebMeIndex() {
        return webMeIndex;
    }

    public void setWebMeIndex(String webMeIndex) {
        this.webMeIndex = webMeIndex;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getWebLogin() {
        return webLogin;
    }

    public void setWebLogin(String webLogin) {
        this.webLogin = webLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "WebInfo{" +
                "id=" + id +
                ", webMainIndex='" + webMainIndex + '\'' +
                ", webMeIndex='" + webMeIndex + '\'' +
                ", pin='" + pin + '\'' +
                ", createDate=" + createDate +
                ", yn=" + yn +
                '}';
    }
}
