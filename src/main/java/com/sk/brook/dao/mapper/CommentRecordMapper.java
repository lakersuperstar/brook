package com.sk.brook.dao.mapper;

import com.sk.brook.dao.domain.CommentRecord;

import java.util.List;

public interface CommentRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentRecord record);

    int insertSelective(CommentRecord record);

    CommentRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentRecord record);

    int updateByPrimaryKey(CommentRecord record);

    List<CommentRecord> selectByWebId(int webId);

}