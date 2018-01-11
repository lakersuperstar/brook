package com.sk.brook.dao.mapper;

import com.sk.brook.dao.domain.WebTask;

import java.util.List;

public interface WebTaskMapper {
    int deleteByPrimaryKey(Integer webId);

    int insert(WebTask record);

    int insertSelective(WebTask record);

    WebTask selectByPrimaryKey(Integer webId);

    int updateByPrimaryKeySelective(WebTask record);

    int updateByPrimaryKey(WebTask record);

    List<WebTask> findWaitingTask();

    List<WebTask> findWaitingTaskByIp(String ip);

    List<WebTask> findAllTask();

    int lockTask(int webId);

    int unlockTask(int webId);

    void resetTaskNumPre();

    void resetTaskOld();

    void addSuccessNum(Integer webId);

}