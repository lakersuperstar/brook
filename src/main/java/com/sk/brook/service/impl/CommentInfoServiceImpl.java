package com.sk.brook.service.impl;

import com.sk.brook.dao.domain.CommentInfo;
import com.sk.brook.dao.mapper.CommentInfoMapper;
import com.sk.brook.service.CommentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by songk on 17/11/23.
 */
@Service("commentInfoService")
public class CommentInfoServiceImpl implements CommentInfoService {

    Logger logger = LoggerFactory.getLogger(CommentInfoServiceImpl.class);

    @Resource(name="commentInfoMapper")
    private CommentInfoMapper commentInfoMapper;

    @Override
    public CommentInfo findCommentInfo(int webId) {
        return commentInfoMapper.selectByWebId(webId);
    }

    @Override
    public boolean increaseCommentInfoVer(int id) {
        return commentInfoMapper.increaseVer(id) == 1 ? true : false;
    }

    @Override
    public CommentInfo findCommentInfoAndIncreaseVer(int webId) {
        CommentInfo ci = commentInfoMapper.selectByWebId(webId);
        try{
            if(ci != null){
                commentInfoMapper.increaseVer(ci.getId());
            }
        }catch (Exception e){
            logger.error("增加Ver版本异常|webId|"+webId,e);
        }

        return ci;
    }

    @Override
    public boolean updateCommentInfoDes(CommentInfo ci) {
        if(ci != null && ci.getWebId() != 0 && ci.getCommentDes() != null){
           int result = commentInfoMapper.updateDesByWebId(ci);
           if(result == 1){
               return true;
           }
        }
        return false;
    }
}
