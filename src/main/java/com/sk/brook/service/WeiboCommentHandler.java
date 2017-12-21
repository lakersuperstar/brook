package com.sk.brook.service;

import com.sk.brook.task.weibo.firefox.AutoCommentRunnable;

import java.util.List;

/**
 * Created by songk on 17/11/24.
 */
public interface WeiboCommentHandler {

    List<AutoCommentRunnable> generaterRunnable();

}
