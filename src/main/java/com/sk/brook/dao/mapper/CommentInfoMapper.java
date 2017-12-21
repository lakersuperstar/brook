package com.sk.brook.dao.mapper;

import com.sk.brook.dao.domain.CommentInfo;

import javax.xml.stream.events.Comment;

public interface CommentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    CommentInfo selectByPrimaryKey(Integer id);

    CommentInfo selectByWebId(Integer webId);

    int increaseVer(Integer id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);

    int updateDesByWebId(CommentInfo ci);
}