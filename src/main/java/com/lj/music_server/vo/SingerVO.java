package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.Singer;
import com.lj.music_server.entity.SingerLanguage;
import com.lj.music_server.entity.SingerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SingerVO extends Singer {
    //创建时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] createTimeRange;
    //更新时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] modifiedTimeRange;
    //歌手类型
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SingerType singerType;
    //歌手语言
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SingerLanguage singerLanguage;
    //排序字段
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sortField;
    //isCollection
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isCollected;
    //loginId
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer loginId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nameStart;
}
