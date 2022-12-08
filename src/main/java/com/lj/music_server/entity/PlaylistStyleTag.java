package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class PlaylistStyleTag {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer playlistStyleId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
}
