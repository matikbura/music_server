package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class MusicVO extends Music {
    //单个歌手ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String singerId;
    //歌手id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Integer> singerIdList;
    //专辑
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Album album;
    //歌手
    @JsonInclude(JsonInclude.Include.NON_NULL)
    ArrayList<Singer> singers;
    //歌单ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer playlistId;
    //歌词
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Lyric lyric;
    //创建时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] createTimeRange;
    //更新时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] updateTimeRange;
    //歌手类型
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SingerType singerType;
    //歌手语言
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SingerLanguage singerLanguage;

}
