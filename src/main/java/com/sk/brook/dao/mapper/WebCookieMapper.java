package com.sk.brook.dao.mapper;

import com.sk.brook.dao.domain.WebCookie;

import java.util.List;

/**
 * Created by songk on 17/11/22.
 */
public interface WebCookieMapper {

    List<WebCookie> findWebCookie(int webId);

    int insert(WebCookie webCookie);

    int deleteByWebId(int webId);

}
