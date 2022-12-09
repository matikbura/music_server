package com.lj.music_server.mapper;

import com.lj.music_server.entity.Comment;
import com.lj.music_server.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CommentMapper {
    ArrayList<Comment> getComment(CommentVO condition);

    Comment getCommentById(Integer targetCommentId);

    void addComment(CommentVO commentVO);
}
