package com.sk.brook.service;

import com.sk.brook.dao.domain.WebCookie;

import java.util.List;

/**
 * Created by songk on 17/11/22.
 */
public interface WebCookieService {
    List<WebCookie> findWebCookie(int webId);
}
