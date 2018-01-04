package com.sk.brook.controller.vo;

/**
 * Created by songk on 17/12/24.
 */
public class CommentInfoVO {
    private    int commentId;
    private    String userName;
    private    String commentInfo;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentInfoVO{" +
                "commentId=" + commentId +
                ", userName='" + userName + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}
