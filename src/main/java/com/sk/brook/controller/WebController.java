package com.sk.brook.controller;

import com.sk.brook.controller.vo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Administrator on 2017/12/22.
 */
@Controller
public class WebController {

    @GetMapping("/user/edit")
    public String toaddWeb(){
        return "addWeb";
    }




    @PostMapping("/user/add")
    public String addWeb(UserInfo userInfo){

        System.out.print(userInfo);
        return "addsuccess";
    }


}
