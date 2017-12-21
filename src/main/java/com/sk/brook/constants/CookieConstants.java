package com.sk.brook.constants;

import com.sk.brook.DateTempUtil;
import org.openqa.selenium.Cookie;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by songk on 17/12/18.
 */
public class CookieConstants {

    public static Set<String> WEB_COOKIE_NAME_SET = new HashSet<String>();


    static {
        WEB_COOKIE_NAME_SET.add("ALF");
        WEB_COOKIE_NAME_SET.add("SUHB");
        WEB_COOKIE_NAME_SET.add("SUB");
        WEB_COOKIE_NAME_SET.add("SSOLoginState");
        WEB_COOKIE_NAME_SET.add("YF-V5-G0");
        WEB_COOKIE_NAME_SET.add("SUBP");
    }

}
