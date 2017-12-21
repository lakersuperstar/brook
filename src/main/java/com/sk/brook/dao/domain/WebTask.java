package com.sk.brook.dao.domain;

import java.util.Date;

/**
 * Created by songk on 17/11/24.
 */
public class WebTask {

    /**
     * 网站ID
     */
    private int webId;
    /**
     * 每次执行一次更新时间
     */
    private Date updateDate;
    /**
     * 是否有效
     */
    private int yn;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 执行次数
     */
    private int num;

    /**
     * 当前执行状态
     */
    private int status;

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
