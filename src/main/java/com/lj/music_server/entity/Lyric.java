package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Lyric {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer musicId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lyric;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tlyric;
}
