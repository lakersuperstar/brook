package com.sk.brook.service.impl;

import com.sk.brook.dao.domain.WebCookie;
import com.sk.brook.dao.mapper.WebCookieMapper;
import com.sk.brook.service.WebCookieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by songk on 17/11/22.
 */
@Service
public class WebCookieServiceImpl implements WebCookieService {

    @Resource(name="webCookieMapper")
    private WebCookieMapper webCookieMapper;

    @Override
    public List<WebCookie> findWebCookie(int webId) {
        List<WebCookie> wcs = webCookieMapper.findWebCookie(webId);
        return wcs;
    }
}
