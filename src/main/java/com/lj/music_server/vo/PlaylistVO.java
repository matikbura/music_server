package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.Playlist;
import com.lj.music_server.entity.PlaylistStyleTag;
import com.lj.music_server.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlaylistVO extends Playlist {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PlaylistStyleTag> tags;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer loginId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isCollected;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer tagId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
}
