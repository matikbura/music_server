package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
@Data
public class Album {

    //专辑id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    //专辑名称
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    //专辑封面
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cover;
    //专辑描述
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    //专辑创建时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createTime;
    //专辑更新时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifiedTime;
    //专辑创建者id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer singerId;
    //点击总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer clickCount;
    //收藏总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer collectionCount;
    //评论总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentCount;
    //歌曲总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer musicCount;
}
