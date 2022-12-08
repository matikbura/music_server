package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

@Data
public class MyCollection {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer typeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //创建时间
    private String createTime;
}
