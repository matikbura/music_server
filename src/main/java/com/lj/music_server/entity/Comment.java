package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Comment {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer targetId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer targetCommentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer typeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createTime;
}
