package com.sk.brook.service.impl;

import com.sk.brook.dao.domain.CommentRecord;
import com.sk.brook.dao.mapper.CommentRecordMapper;
import com.sk.brook.service.CommentRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Created by songk on 17/11/27.
 */
@Service("commentRecordService")
public class CommentRecordServiceImpl implements CommentRecordService {

    @Resource(name = "commentRecordMapper")
    CommentRecordMapper commentRecordMapper;

    @Override
    public CommentRecord randomFindRecord(int webId) {
        List<CommentRecord> crs = commentRecordMapper.selectByWebId(webId);
        CommentRecord cr = null;
        if (crs != null && crs.size() != 0) {
            Random r = new Random();
            int rint = r.nextInt(crs.size());
            cr = crs.get(rint);
        }
        return cr;
    }
}
