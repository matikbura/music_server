package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Singer {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    //歌手名称
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    //歌手简介
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String introduction;
    //封面
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cover;
    //创建时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createTime;
    //更新时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifiedTime;
    //歌曲点击总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer clickCount;
    //歌曲收藏总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer collectionCount;
    //歌曲评论总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentCount;
    //歌手类型ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer singerTypeId;
    //歌手语言ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer singerLanguageId;
    //专辑总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer albumCount;
    //歌曲总量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer musicCount;
}
