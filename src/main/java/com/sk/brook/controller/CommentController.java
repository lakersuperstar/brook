package com.sk.brook.controller;

import com.sk.brook.controller.vo.CommentInfoVO;
import com.sk.brook.dao.domain.CommentRecord;
import com.sk.brook.dao.domain.WebInfo;
import com.sk.brook.dao.mapper.CommentRecordMapper;
import com.sk.brook.dao.mapper.WebInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songk on 17/12/24.
 */
@Controller
public class CommentController {

    @Resource(name="commentRecordMapper")
    private CommentRecordMapper commentRecordMapper;

    @Resource(name="webInfoMapper")
    private WebInfoMapper webInfoMapper;

    @GetMapping("/comment/edit")
    private String toAddComment(){
        return "addcomment";
    }

    @PostMapping("/comment/add")
    @ResponseBody
    private String addComment(CommentInfoVO infoVO){
        if(infoVO == null || StringUtils.isBlank(infoVO.getCommentInfo()) || StringUtils.isBlank(infoVO.getUserName())){
            return "nullerror";
        }
        try{
            CommentRecord cr = new CommentRecord();
            cr.setCommentDes(infoVO.getCommentInfo());
            WebInfo webInfo = webInfoMapper.findWebInfoByLoginUserName(infoVO.getUserName());
            if(webInfo != null){
                cr.setWebId(webInfo.getId());
            }else{
                return "nameerror";
            }
            commentRecordMapper.insert(cr);
            return "addsuccess";
        }catch (Exception e){
            return "error";
        }

    }
    @PostMapping("/comment/del")
    @ResponseBody
    private String delComment(CommentInfoVO infoVO){
        if(infoVO == null || StringUtils.isBlank(infoVO.getCommentInfo()) || infoVO.getCommentId() ==0 || StringUtils.isBlank(infoVO.getUserName())){
            return "nullerror";
        }
        try{
            WebInfo webInfo = webInfoMapper.findWebInfoByLoginUserName(infoVO.getUserName());
            if(webInfo != null){
                CommentRecord cr = commentRecordMapper.selectByPrimaryKey(infoVO.getCommentId());
                if(cr!= null && cr.getWebId() == webInfo.getId()){
                   int del =  commentRecordMapper.deleteByPrimaryKey(infoVO.getCommentId());
                   if(del == 1){
                       return "success";
                   }
                }
            }else{
                return "error";
            }
            return "error";
        }catch (Exception e){
            return "error";
        }
    }




    @GetMapping("/comment/list")
    private ModelAndView toListComment(String u){
        ModelAndView view = new ModelAndView();
        view.setViewName("commentlist");
        List<CommentInfoVO> commentInfoVOList = new ArrayList<CommentInfoVO>();

        if(StringUtils.isBlank(u)){
            view.addObject("status","0");
            return view;
        }
        try{
            WebInfo webInfo = webInfoMapper.findWebInfoByLoginUserName(u);
            if(webInfo != null){
                List<CommentRecord>  commentRecord = commentRecordMapper.selectByWebId(webInfo.getId());
                if(commentRecord != null){
                    for(CommentRecord cr : commentRecord){
                        CommentInfoVO commentInfoVO = new CommentInfoVO();
                        commentInfoVO.setCommentId(cr.getId());
                        commentInfoVO.setCommentInfo(cr.getCommentDes());
                        commentInfoVO.setUserName(u);
                        commentInfoVOList.add(commentInfoVO);
                    }
                }
                view.addObject("status","1");
                view.addObject("li",commentInfoVOList);
                return view;
            }else{
                view.addObject("status","2");
                return view;
            }
        }catch (Exception e){
            view.addObject("status","-1");
            return view;
        }

    }


}
