package com.sk.brook.service.holder;

import com.sk.brook.dao.mapper.WebCookieMapper;
import com.sk.brook.dao.mapper.WebTaskMapper;
import com.sk.brook.service.CommentInfoService;
import com.sk.brook.service.WebCookieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by songk on 17/11/24.
 */
@Service("serviceHolder")
public class ServiceHolder {
    @Resource(name="commentInfoService")
    private CommentInfoService commentInfoService;
    @Resource(name="webTaskMapper")
    private WebTaskMapper webTaskMapper;
    @Resource(name="webCookieMapper")
    private WebCookieMapper webCookieMapper;
    @Resource
    private WebCookieService webCookieService;


    public CommentInfoService getCommentInfoService() {
        return commentInfoService;
    }



    public WebTaskMapper getWebTaskMapper() {
        return webTaskMapper;
    }

    public WebCookieMapper getWebCookieMapper() {
        return webCookieMapper;
    }

    public WebCookieService getWebCookieService() {
        return webCookieService;
    }

    public void setWebCookieService(WebCookieService webCookieService) {
        this.webCookieService = webCookieService;
    }
}
