package com.lj.music_server.controller;

import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.service.ICommentService;
import com.lj.music_server.vo.CommentVO;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {
    final ICommentService commentService;
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("getCommentConditionPage")
    public Result<Object> getCommentConditionPage(@RequestBody Page<CommentVO> page) {
        if (page.getCondition() == null||page.getCondition().getType()==null||page.getCondition().getTypeId()==null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST,"参数错误");
        }
        commentService.getCommentConditionPage(page);
        return new Result<>(true, HttpStatusEnum.OK, page);
    }
    @RequestMapping("addComment")
    public Result<Object> addComment(@RequestBody CommentVO commentVO) {
        if (commentVO.getType()==null||commentVO.getTypeId()==null||commentVO.getUserId()==null||commentVO.getContent()==null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST,"参数错误");
        }
        commentService.addComment(commentVO);
        return new Result<>(true, HttpStatusEnum.OK, commentVO);
    }
}
