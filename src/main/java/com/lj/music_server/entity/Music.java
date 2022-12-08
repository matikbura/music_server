package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Music {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    //音乐名称
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    //音乐的地址
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;
    //播放时间   格式  分钟:秒
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String duration;
    //播放时长 格式 秒
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer durationSecond;
    //创建时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createTime;
    //更新时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifiedTime;
    //mv地址
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mvUrl;
    //mv时长
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mvDuration;
    //专辑id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer albumId;
    //点击量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer clickCount;
    //收藏量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer collectionCount;
    //评论量
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentCount;
    //歌手类型ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer singerTypeId;
    //歌手语言ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer singerLanguageId;
}
