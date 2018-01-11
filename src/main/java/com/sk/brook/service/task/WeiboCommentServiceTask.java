package com.sk.brook.service.task;

import com.sk.brook.dao.domain.CommentInfo;
import com.sk.brook.dao.domain.CommentRecord;
import com.sk.brook.dao.domain.WebCookie;
import com.sk.brook.dao.domain.WebTask;
import com.sk.brook.dao.mapper.WebTaskMapper;
import com.sk.brook.service.CommentInfoService;
import com.sk.brook.service.CommentRecordService;
import com.sk.brook.service.WebCookieService;
import com.sk.brook.service.WeiboCommentHandler;
import com.sk.brook.task.weibo.firefox.AutoCommentRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by songk on 17/11/22.
 */
@Component
public class WeiboCommentServiceTask {

    private static Logger logger = LoggerFactory.getLogger(WeiboCommentServiceTask.class);

    @Resource
    private WebCookieService webCookieService;

    @Resource
    private WeiboCommentHandler weiboCommentHandler;

    @Resource
    private CommentInfoService commentInfoService;

    @Resource
    private CommentRecordService commentRecordService;

    @Resource(name="webTaskMapper")
    private WebTaskMapper webTaskMapper;

    private  ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();



    @Scheduled(fixedDelay = 60 * 1000)
    public void start() {
        logger.info("开始执行任务");
        List<AutoCommentRunnable> autoCommentRunnables = weiboCommentHandler.generaterRunnable();
        logger.info("查询到的执行任务数量："+autoCommentRunnables);
        if (autoCommentRunnables != null){
            for(AutoCommentRunnable runnable: autoCommentRunnables){
                singleThreadExecutor.execute(runnable);
            }
        }

    }

    @Scheduled(fixedDelay = 5 * 1000)
    public void updateCommentDes(){
        try{
            this.executeUpdateCommentDes();
        }catch (Exception e){
            logger.error("执行更新最新内容work异常",e);
        }
    }


    @Scheduled(fixedDelay = 5 * 1000)
    public void resetTask(){
        try{
            webTaskMapper.resetTaskNumPre();
            webTaskMapper.resetTaskOld();
        }catch (Exception e){
            logger.error("重置没有执行成功的任务异常",e);
        }
    }



    private void executeUpdateCommentDes(){
        List<WebTask> waitTasks = webTaskMapper.findAllTask();
        if(waitTasks == null || waitTasks.size() <= 0){
            return;
        }
        for(WebTask wt : waitTasks){
            try{
                CommentRecord cr = commentRecordService.randomFindRecord(wt.getWebId());
                CommentInfo ci = new CommentInfo();
                if(cr!= null){
                    ci.setWebId(cr.getWebId());
                    ci.setCommentDes(cr.getCommentDes());
                    commentInfoService.updateCommentInfoDes(ci);
                }
            }catch (Exception e){
                logger.error("更新最新的发布内容异常",e);
            }

        }

    }






}
