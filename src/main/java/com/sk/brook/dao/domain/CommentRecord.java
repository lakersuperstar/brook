package com.sk.brook.dao.domain;

public class CommentRecord {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 网站ID
     */
    private Integer webId;

    /**
     * 要发表的信息
     */
    private String commentDes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    public String getCommentDes() {
        return commentDes;
    }

    public void setCommentDes(String commentDes) {
        this.commentDes = commentDes == null ? null : commentDes.trim();
    }
}