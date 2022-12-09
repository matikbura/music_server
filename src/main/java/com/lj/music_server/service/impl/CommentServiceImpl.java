package com.lj.music_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lj.music_server.entity.Comment;
import com.lj.music_server.mapper.CommentMapper;
import com.lj.music_server.mapper.UserMapper;
import com.lj.music_server.service.ICommentService;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.CommentVO;
import com.lj.music_server.vo.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentServiceImpl implements ICommentService {
    final CommentMapper commentMapper;
    final UserMapper userMapper;
    public CommentServiceImpl(CommentMapper commentMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void getCommentConditionPage(Page<CommentVO> page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Comment> comments= commentMapper.getComment(page.getCondition());
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        ArrayList<CommentVO> commentVOS = ParseUtils.po_parse_vo(pageInfo.getList(), CommentVO.class);
        for (CommentVO commentVO : commentVOS) {
            commentVO.setUser(userMapper.getUserById(commentVO.getUserId()));
            if (commentVO.getTargetCommentId()!=null) {
                commentVO.setTargetComment(commentMapper.getCommentById(commentVO.getTargetCommentId()));
            }
            if (commentVO.getTargetId()!=null) {
                commentVO.setTargetUser(userMapper.getUserById(commentVO.getTargetId()));
            }
        }
        page.setData(commentVOS);
        page.setTotal(pageInfo.getTotal());
    }
}
