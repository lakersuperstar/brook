package com.sk.brook.dao.domain;

import java.util.Date;

/**
 * Created by songk on 17/11/22.
 */
public class WebCookie {

    /**
     * 主键
     */
    private int id;

    /**
     * cookie名称
     */
    private String cookieName;

    /**
     * cookie值
     */
    private String cookieValue;

    /**
     * cookie域
     */
    private String webDomain;

    /**
     * cookie path
     */
    private String path;

    /**
     * cookie所属网站ID
     */
    private int webId;

    /**
     * 状态，是否可用
     */
    private int status;

    /**
     * 过期时间
     */
    private Date expireDate;

    /**
     * 是否有效
     */
    private int yn;

    /**
     * 记录创建时间
     */
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getWebDomain() {
        return webDomain;
    }

    public void setWebDomain(String webDomain) {
        this.webDomain = webDomain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




    @Override
    public String toString() {
        return "WebCookie{" +
                "id=" + id +
                ", cookieName='" + cookieName + '\'' +
                ", cookieValue='" + cookieValue + '\'' +
                ", webDomain='" + webDomain + '\'' +
                ", path='" + path + '\'' +
                ", webId=" + webId +
                ", status=" + status +
                ", expireDate=" + expireDate +
                ", yn=" + yn +
                ", createDate=" + createDate +
                '}';
    }
}
