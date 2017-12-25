package com.sk.brook.controller;

import com.sk.brook.dao.domain.CommentRecord;
import com.sk.brook.dao.mapper.CommentRecordMapper;
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

    @PostMapping("/comment/add")
    private String addComment(CommentRecord commentRecord){


        return "";
    }
}
