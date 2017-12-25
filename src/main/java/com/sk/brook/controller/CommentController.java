package com.sk.brook.controller;

import com.sk.brook.controller.vo.CommentInfoVO;
import com.sk.brook.dao.domain.CommentRecord;
import com.sk.brook.dao.domain.WebInfo;
import com.sk.brook.dao.mapper.CommentRecordMapper;
import com.sk.brook.dao.mapper.WebInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * Created by songk on 17/12/24.
 */
@Controller
public class CommentController {

    @Resource(name="commentRecordMapper")
    private CommentRecordMapper commentRecordMapper;

    @Resource(name="webInfoMapper")
    private WebInfoMapper webInfoMapper;

    @PostMapping("/comment/add")
    private String addComment(CommentInfoVO infoVO){

        if(infoVO == null || StringUtils.isBlank(infoVO.getCommentInfo()) || StringUtils.isBlank(infoVO.getUserName())){
            return "";
        }
        CommentRecord cr = new CommentRecord();
        cr.setCommentDes(infoVO.getCommentInfo());
        WebInfo webInfo = webInfoMapper.findWebInfoByLoginUserName(infoVO.getUserName());
        if(webInfo != null){
            cr.setWebId(webInfo.getId());
        }else{
            return "";
        }
        commentRecordMapper.insert(cr);
        return "";
    }
}
