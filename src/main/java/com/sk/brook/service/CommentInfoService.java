package com.sk.brook.service;

import com.sk.brook.dao.domain.CommentInfo;

/**
 * Created by songk on 17/11/23.
 */
public interface CommentInfoService {
    CommentInfo findCommentInfo(int webId);
    boolean increaseCommentInfoVer(int id);

    CommentInfo findCommentInfoAndIncreaseVer(int webId);

    boolean updateCommentInfoDes(CommentInfo ci);
}
