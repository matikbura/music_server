package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.Comment;
import com.lj.music_server.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentVO extends Comment {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User targetUser;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Comment targetComment;
}
