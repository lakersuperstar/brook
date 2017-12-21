package com.sk.brook.dao.mapper;

import com.sk.brook.dao.domain.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String pin);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String pin);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}