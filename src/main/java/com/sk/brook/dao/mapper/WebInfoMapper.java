package com.sk.brook.dao.mapper;

import com.sk.brook.dao.domain.WebInfo;

public interface WebInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebInfo record);

    int insertSelective(WebInfo record);

    WebInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebInfo record);

    int updateByPrimaryKey(WebInfo record);

    WebInfo findWebInfoByLoginUserName(String userName);
}