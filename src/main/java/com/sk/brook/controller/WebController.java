package com.sk.brook.controller;

import com.sk.brook.controller.vo.UserInfo;
import com.sk.brook.dao.domain.WebInfo;
import com.sk.brook.dao.mapper.WebInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/22.
 */
@Controller
public class WebController {

    @Resource(name="webInfoMapper")
    private WebInfoMapper webInfoMapper;



    @GetMapping("/user/edit")
    public String toaddWeb(){
        return "addweb";
    }




    @PostMapping("/user/add")
    @ResponseBody
    public String addWeb(UserInfo userInfo){
       if(userInfo == null || StringUtils.isBlank(userInfo.getPassword())|| StringUtils.isBlank(userInfo.getUserName()) ){
           return "登录名和密码不能为空！";
       }
        WebInfo webInfo = new WebInfo();
        webInfo.setUserName(userInfo.getUserName());
        webInfo.setUserPwd(userInfo.getPassword());
        webInfo.setWebLogin("login");
        webInfo.setWebMainIndex("https://weibo.com");
        if(StringUtils.isBlank(userInfo.getMeIndex())){
            webInfo.setWebMeIndex("https://weibo.com");
        }else{
            webInfo.setWebMeIndex(userInfo.getMeIndex());
        }
        try{
            webInfoMapper.insert(webInfo);
            return "addsuccess";
        }catch (Exception e){
            if(e.getMessage().contains("Duplicate entry")){
                return "unique fail";
            }else{
                return "error";
            }
        }


    }
}
