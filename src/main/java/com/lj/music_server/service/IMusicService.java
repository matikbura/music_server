package com.lj.music_server.service;

import com.lj.music_server.entity.Lyric;
import com.lj.music_server.entity.Music;
import com.lj.music_server.vo.MusicVO;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.PlaylistVO;

import java.util.ArrayList;

public interface IMusicService {
    ArrayList<MusicVO> getMusic(MusicVO musicVO);

    void addMusic(MusicVO music);

    ArrayList<MusicVO> top(Integer limit);
    ArrayList<MusicVO> getPlaylistMusic(MusicVO musicVO);
    MusicVO getMusicById(Integer id);

    void addMusicToPlaylist(MusicVO musicVO);

    Lyric getLyricByMusicId(Integer id);

    void getMusicDetailPage(Page<MusicVO> page);

    void getMusicConditionPage(Page<MusicVO> page);
}
