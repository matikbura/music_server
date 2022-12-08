package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.Album;
import com.lj.music_server.entity.Singer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlbumVO extends Album {
    //创建时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] createTimeRange;
    //更新时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] modifiedTimeRange;
    //歌手
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Singer singer;
}
