package com.sk.brook;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Created by songk on 17/11/21.
 */
public class HttpClientTest {

    public static void main(String[] args)throws  Exception{

//        CloseableHttpClient httpclient = HttpClients.createDefault();
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        HttpPost post = new HttpPost("https://m.weibo.cn/api/comments/create");
        BasicClientCookie cookie = new BasicClientCookie("SSOLoginState", "1511184836");
        cookie.setVersion(0);
        cookie.setDomain(".weibo.cn");   //设置范围
        cookie.setPath("/");
        cookie.setExpiryDate(DateTempUtil.parseString("2017-12-15 07:09:57"));
        cookieStore.addCookie(cookie);


        BasicClientCookie cookie1 = new BasicClientCookie("SUB", "_2A253FqnODeRhGeRP6FoS8C7JyTmIHXVU-DeGrDV6PUJbktANLRPskW1NHetkT5wkuTD3AoDSCgHCR_6G8T7vfMRO");
        cookie1.setVersion(0);
        cookie1.setDomain(".weibo.cn");   //设置范围
        cookie1.setPath("/");
        cookie1.setExpiryDate(DateTempUtil.parseString("2017-12-15 07:09:57"));
        cookieStore.addCookie(cookie1);

        // 建立一个NameValuePair数组，用于存储欲传送的参数
        post.addHeader("Content-type","application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");

        System.out.println("cookie store:" + cookieStore.getCookies());

        CommentWbInfo cwi = new CommentWbInfo();
        cwi.setContent("w我是什么aaa!1#￥");
        cwi.setId(4176275792688473l);
        cwi.setMid(4176275792688473l);
        cwi.setSt("6c1657");
        String jsonString = JSONObject.toJSONString(cwi);
        System.out.println(jsonString);
        post.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));

        CloseableHttpResponse response =httpClient.execute(post);


        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
    }
}
