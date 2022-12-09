package com.lj.music_server.service;

import com.lj.music_server.entity.PlaylistStyle;
import com.lj.music_server.entity.PlaylistStyleTag;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.PlaylistStyleVo;
import com.lj.music_server.vo.PlaylistVO;

import java.util.ArrayList;

public interface IPlaylistService {
    ArrayList<PlaylistVO> top(Integer limit);

    ArrayList<PlaylistVO> getPlaylist(PlaylistVO playlist);

    int addPlaylist(PlaylistVO playlist);

    void updatePlaylist(PlaylistVO playlist);


    PlaylistVO getPlaylistById(Integer id, Integer userId);

    ArrayList<PlaylistStyleVo> getPlaylistStyle();

    void addPlaylistStyle(PlaylistStyle style);

    void addPlaylistStyleTag(PlaylistStyleTag style);

    void getPlaylistConditionPage(Page<PlaylistVO> page);
}
