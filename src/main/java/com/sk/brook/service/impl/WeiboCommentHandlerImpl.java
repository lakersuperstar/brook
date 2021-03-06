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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songk on 17/11/24.
 */
@Service
public class WeiboCommentHandlerImpl implements WeiboCommentHandler {

    private static Logger logger = LoggerFactory.getLogger(WeiboCommentHandlerImpl.class);

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
        String hostAddress = "";
        try{
            InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            hostAddress = address.getHostAddress();
            logger.info(hostAddress);
        }catch (Exception e){
            logger.error("查询本地Ip异常",e);
        }
        Map<String,Object> mapParm = new HashMap<String,Object>();
        mapParm.put("ipStr",hostAddress);
        mapParm.put("num",150);
        List<WebTask> waitTasks = webTaskMapper.findWaitingTaskByIp(mapParm);
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
