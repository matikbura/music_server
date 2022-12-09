package com.lj.music_server.service;

import com.lj.music_server.vo.CommentVO;
import com.lj.music_server.vo.Page;

public interface ICommentService {
    void getCommentConditionPage(Page<CommentVO> page);

    void addComment(CommentVO commentVO);
}
