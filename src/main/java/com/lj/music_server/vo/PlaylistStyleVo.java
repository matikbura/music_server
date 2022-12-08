package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.PlaylistStyle;
import com.lj.music_server.entity.PlaylistStyleTag;
import lombok.Data;

import java.util.ArrayList;
@Data
public class PlaylistStyleVo extends PlaylistStyle {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<PlaylistStyleTag> tags;
}
