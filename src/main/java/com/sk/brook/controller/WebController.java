package com.sk.brook.controller;

import com.sk.brook.controller.vo.UserInfo;
import com.sk.brook.dao.domain.WebInfo;
import com.sk.brook.dao.mapper.WebInfoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "addWeb";
    }




    @PostMapping("/user/add")
    public String addWeb(UserInfo userInfo){
        WebInfo webInfo = new WebInfo();
        webInfo.setUserName(userInfo.getUserName());
        webInfo.setUserPwd(userInfo.getPassword());
        webInfo.setWebLogin("login");
        webInfo.setWebMainIndex("https://weibo.com");
        webInfo.setWebMainIndex(userInfo.getMeIndex());
        webInfoMapper.insert(webInfo);
        return "addsuccess";
    }


}
