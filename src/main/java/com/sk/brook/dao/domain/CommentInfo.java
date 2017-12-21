package com.sk.brook.dao.domain;

import java.util.Date;

/**
 * Created by songk on 17/11/23.
 */
public class CommentInfo {

    /**
     * 主键
     */
    private int id;
    /**
     * 网站ID
     */
    private int webId;
    /**
     * 评论内容
     */
    private String commentDes;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 版本号，用来更新每次从小往大顺序获取，获取一次将获取的加1
     */
    private int ver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public String getCommentDes() {
        return commentDes;
    }

    public void setCommentDes(String commentDes) {
        this.commentDes = commentDes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "id=" + id +
                ", webId=" + webId +
                ", commentDes='" + commentDes + '\'' +
                ", createDate=" + createDate +
                ", ver=" + ver +
                '}';
    }
}
