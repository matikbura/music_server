package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
@Data
public class Playlist {

    //歌单id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    //歌单名称
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    //歌单封面
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cover;
    //歌单描述
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    //歌单创建时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createTime;
    //歌单更新时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifiedTime;
    //歌单创建者id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;
    //点击量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer clickCount;
    //收藏量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer collectionCount;
    //评论量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentCount;
    //歌曲总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer musicCount;
}
