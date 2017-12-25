package com.sk.brook.controller.vo;

/**
 * Created by songk on 17/12/24.
 */
public class CommentInfoVO {
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

    @Override
    public String toString() {
        return "CommentInfoVO{" +
                "userName='" + userName + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}
