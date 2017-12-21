package com.sk.brook.service.impl;

import com.sk.brook.dao.domain.CommentInfo;
import com.sk.brook.dao.domain.WebCookie;
import com.sk.brook.dao.domain.WebInfo;
import com.sk.brook.dao.domain.WebTask;
import com.sk.brook.dao.mapper.CommentInfoMapper;
import com.sk.brook.dao.mapper.WebInfoMapper;
import com.sk.brook.dao.mapper.WebTaskMapper;
import com.sk.brook.service.WebCookieService;
import com.sk.brook.service.WeiboCommentHandler;
import com.sk.brook.service.holder.ServiceHolder;
import com.sk.brook.task.weibo.firefox.AutoCommentRunnable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songk on 17/11/24.
 */
@Service
public class WeiboCommentHandlerImpl implements WeiboCommentHandler {

    @Resource
    private WebCookieService webCookieService;

    @Resource(name="webTaskMapper")
    private WebTaskMapper webTaskMapper;

    @Resource(name="webInfoMapper")
    private WebInfoMapper webInfoMapper;

    @Resource(name="commentInfoMapper")
    private CommentInfoMapper commentInfoMapper;

    @Resource
    private ServiceHolder serviceHolder;

    @Override
    public List<AutoCommentRunnable> generaterRunnable() {
        List<AutoCommentRunnable> runnables = new ArrayList<AutoCommentRunnable>();
        List<WebTask> waitTasks = webTaskMapper.findWaitingTask();
        if(waitTasks != null){
            for(WebTask webTask : waitTasks){
                WebInfo webInfo = webInfoMapper.selectByPrimaryKey(webTask.getWebId());
                if(webInfo == null){
                    continue;
                }
                CommentInfo commentInfo = commentInfoMapper.selectByWebId(webTask.getWebId());
                if(commentInfo == null){
                    continue;
                }
                AutoCommentRunnable runnable = new AutoCommentRunnable(webInfo,commentInfo,webTask,this.serviceHolder);
                runnables.add(runnable);
            }
        }
       return runnables;
    }
}
